

let quill = new Quill('#editor-container', {
    modules:{
        toolbar: [
            ['bold', 'italic', 'underline', 'strike'],        // 텍스트 스타일
            ['blockquote', 'code-block'],                      // 인용 및 코드 블록
            // [{ 'header': 1 }, { 'header': 2 }],                // 제목
            [{ 'list': 'ordered' }, { 'list': 'bullet' }],     // 목록
            [{ 'script': 'sub' }, { 'script': 'super' }],      // 아래 첨자, 위 첨자
            [{ 'indent': '-1' }, { 'indent': '+1' }],          // 들여쓰기
            // [{ 'direction': 'rtl' }],                          // 텍스트 방향
            [{ 'size': ['small', false, 'large', 'huge'] }],   // 글자 크기
            [{ 'header': [1, 2, 3, 4, 5, 6, false] }],          // 다양한 제목 크기
            [{ 'color': [] }, { 'background': [] }],           // 글자 색상, 배경 색상
            [{ 'font': [] }],                                  // 글꼴 선택
            [{ 'align': [] }],                                 // 텍스트 정렬
            ['clean'],                                         // 형식 제거
            ['link', 'image', 'video']
        ]
    },
    placeholder: '여기에 작성하세요',
    theme: 'snow'  // or 'bubble'
});



quill.getModule('toolbar').addHandler('image', function () {
    selectLocalImage();
});

const maxLength = 2000
/**
 * 2000자 이상 작성시 제한
 */
quill.on('text-change', function() {
    document.getElementById("quill_html").value = quill.root.innerHTML;
});
quill.on('editor-change', e => {
    const length = quill.getLength()
    if (length > maxLength+1){
        quill.deleteText(maxLength, length);
        alert("최대 글자수 도달 : 2000자");
    }
});

function selectLocalImage(){
    const fileInput = document.createElement('input');
    fileInput.setAttribute('type', 'file');
    console.log("input.type " + fileInput.type);

    fileInput.click();

    fileInput.addEventListener("change", function () {  // change 이벤트로 input 값이 바뀌면 실행
        const formData = new FormData();
        const file = fileInput.files[0];

        formData.append('file', file);

        console.log(formData);

        $.ajax({
            type: 'post',
            enctype: 'multipart/form-data',
            url: '/upGallery/write/image',
            data: formData,
            processData: false,
            contentType: false,
            dataType: 'json',
            success: function (data) {
                const range = quill.getSelection(); // 사용자가 선택한 에디터 범위

                quill.insertEmbed(range.index, 'image', "/upGallery/write/image/" + data.attachFile.storeFileName +"?date="+data.uploadDate);

                console.log("성공",data);
                console.log("경로","/upGallery/write/image/" + data.attachFile.storeFileName +"?date="+data.uploadDate)
            },
            error: function (err) {
                console.log(err);
            }
        });

    })
}
function write_submit(){
    let tag = document.querySelector('.upCycleTag').value;
    let title = document.querySelector('.titleSpace').value;
    let contents = quill.root.innerHTML;
    if (title===null || title===''){
        alert("제목은 최소 2글자 이상 필수입니다.")
    }
    else if(title.size <2){
        alert("제목은 2글자 이상이어야 합니다.")
    }
    else if(contents=== null || contents==='' || contents.length<2){
        alert("내용은 2글자 이상이어야 합니다.")
    }
    else {
        $.ajax({
            url: '/upGallery/write',
            type: 'POST',
            data: JSON.stringify({ title: title,tag: tag, contents: contents }),
            contentType: 'application/json',
            success: function(response) {
                console.log("성공 : ",response);

                location.replace( "/upGallery/views/"+response);

            },
            error: function(e) {
                console.log("실패 : ",e);
            }
        });
    }

}