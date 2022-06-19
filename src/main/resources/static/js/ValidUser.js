function sendToUser() {

    const USER_NAME_NOT_UNIQUE = "Please use another username";
    const WRONG_USER_NAME_LENGTH = "The username must be at least 5 symbols, up to 50 symbols";
    const WRONG_USER_NAME_SYMBOLS = "Use only latin symbols and digits";
    const WRONG_USER_PASSWORD_LENGTH = "The password must be at least 8 symbols, up to 100 symbols";

    let usernameErrorField = document.querySelector('.usernameErrorField');
    usernameErrorField.innerHTML = null;
    let passwordErrorField = document.querySelector('.passwordErrorField');
    passwordErrorField.innerHTML = null;

    let name = document.querySelector('#username');
    let password = document.querySelector("#password");
    let request = new XMLHttpRequest();

    let url = "/user/registration";
    request.open("POST", url, true);
    request.setRequestHeader("Content-Type", "application/json");
    request.responseType = 'json'
    request.onreadystatechange = function () {
        if (request.readyState === 4 && request.status === 200) {
            const operationStatus = request.response;
            if (operationStatus.success === true) {
                window.location.href = '/login';
            } else {
                operationStatus.errors.forEach(function(error) {
                    switch (error){
                        case 'USER_NAME_NOT_UNIQUE' :
                            usernameErrorField.innerHTML = USER_NAME_NOT_UNIQUE;
                            break;
                        case 'WRONG_USER_NAME_LENGTH':
                            usernameErrorField.innerHTML = WRONG_USER_NAME_LENGTH;
                            break;
                        case 'WRONG_USER_NAME_SYMBOLS':
                            usernameErrorField.innerHTML = WRONG_USER_NAME_SYMBOLS;
                            break;
                        case 'WRONG_USER_PASSWORD_LENGTH':
                            passwordErrorField.innerHTML = WRONG_USER_PASSWORD_LENGTH;
                            break;
                    }
                })
            }}
    };
    const data = JSON.stringify({"name": name.value, "password": password.value});
    request.send(data);
}