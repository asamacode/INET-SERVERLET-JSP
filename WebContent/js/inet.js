$('#datepicker').datepicker({
    uiLibrary: 'bootstrap4'
});

$('#timepicker').timepicker({
    uiLibrary: 'bootstrap4'
});

function validateUseTime(inputField) {
    var time = inputField.value;
    if (time !== '') {
        if (time > 0)
            inputField.style.backgroundColor = '#bfa';
        else inputField.style.backgroundColor = '#fba';
    } else
        inputField.style.backgroundColor = '#fba';
}

function validateHhMm(inputField) {
    var isValid = /^([0-1]?[0-9]|2[0-4]):([0-5][0-9])(:[0-5][0-9])?$/.test(inputField.value);

    if (isValid) {
        inputField.style.backgroundColor = '#bfa';
    } else {
        inputField.style.backgroundColor = '#fba';
    }

    return isValid;
}

function validateDate(inputField) {
    var reg = /(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\d\d/;
    if (inputField.value.match(reg)) {
        inputField.style.backgroundColor = '#bfa';
    } else {
        inputField.style.backgroundColor = '#fba';
    }
}

function validateQuantity(inputField) {
    var price = inputField.value;
    if (price !== '') {
        if (price > 0)
            inputField.style.backgroundColor = '#bfa';
        else inputField.style.backgroundColor = '#fba';
    } else
        inputField.style.backgroundColor = '#fba';
}

function validateEmail(inputField) {
    var re = /\S+@\S+\.\S+/.test(inputField.value);

    if (re) {
        inputField.style.backgroundColor = '#bfa';
    } else {
        inputField.style.backgroundColor = '#fba';
    }
}

function validatePhone(inputField) {
    var vnf_regex = /((09|03|07|08|05|849|843|847|848|845)+([0-9]{8})\b)/g;
    var mobile = inputField.value;

    if (mobile !== '') {
        if (mobile.length >= 10 && mobile.length <= 11) {
            if (vnf_regex.test(mobile) == false) {
                inputField.style.backgroundColor = '#fba';
            } else {
                inputField.style.backgroundColor = '#bfa';
            }
        } else {
            inputField.style.backgroundColor = '#fba';
        }
    } else {
        inputField.style.backgroundColor = '#fba';
    }
}

function validateMaKH(inputField) {
    var isMatch = /^KH\d{5}$/.test(inputField.value);
    
    if (isMatch) {
        inputField.style.backgroundColor = '#bfa';
    } else {
        inputField.style.backgroundColor = '#fba';
    }
}

function validateMaDV(inputField) {
    var isMatch = /^DV\d{3}$/.test(inputField.value);
    
    if (isMatch) {
        inputField.style.backgroundColor = '#bfa';
    } else {
        inputField.style.backgroundColor = '#fba';
    }
}