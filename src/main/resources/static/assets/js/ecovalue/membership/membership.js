function validateForm(){
    if (grecaptcha.getResponse()){
        return true;
    }
    else{
        alert("로봇이 아닙니다를 체크하세요")
        return false;
    }
}

function initPostcode() {
    let postcodeBtn = document.getElementById('postcode-find');
    let postCodeInput = document.getElementById('postcode-box');
    let addressInput =  document.getElementById('address-box');

    postcodeBtn.addEventListener('click', function() {
        new daum.Postcode({
            oncomplete: function(data) {
                addressInput.value = data.address;
                postCodeInput.value = data.zonecode;
            }
        }).open();
    });
}
initPostcode();
