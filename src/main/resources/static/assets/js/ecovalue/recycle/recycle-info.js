const btnArr = document.getElementsByTagName('button');

for(let i = 0; i < btnArr.length; i++) {
    btnArr[i].addEventListener('click', function (e) {
        e.preventDefault();
        document.querySelector('#parts'+(i+1)).scrollIntoView(true);

    });
} //for