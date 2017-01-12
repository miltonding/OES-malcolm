$(function() {
    var queryString = location.hash;
    $("#queryString").val(queryString);
    var storage = window.localStorage;
    var username = storage.getItem("userName");
    var password = storage.getItem("password");
    
    if (username && password) {
        $("#userName").val(username);
        $("#password").val(password);
        $("#customCheckbox").attr("checked", "checked");
    }
    $('#password').bind('keypress',function(event){
        if(event.keyCode == "13")    
        {
            login();
        }
    });
    function login() {
        var loginFormObj = $("#loginForm");
        var errorMsgObj = $("#errorMsg");
        var userNameErrorIcon = $("#userNameErrorIcon");
        var passwordErrorIcon = $("#passwordErrorIcon");
        
        var userNameValue = $("#userName").val();
        var isSubmitForm = true;
        var errorMsg = "";
        if (!userNameValue) {
            errorMsg = "user name is required";
            userNameErrorIcon.css("visibility", "visible");
            isSubmitForm = false;
        } else {
            userNameErrorIcon.css("visibility", "hidden");
            errorMsgObj.val("");
        }
        var passwordValue = $("#password").val();
        if (!passwordValue) {
            errorMsg = "password is required";
            passwordErrorIcon.css("visibility", "visible");
            isSubmitForm = false;
        } else {
            passwordErrorIcon.css("visibility", "hidden");
            errorMsgObj.val("");
        }
        if (!passwordValue && !userNameValue) {
            errorMsg = "username and password is required";
            userNameErrorIcon.css("visibility", "visible");
            passwordErrorIcon.css("visibility", "visible");
            isSubmitForm = false;
        } else {
            errorMsgObj.val("");
        }
        if (!isSubmitForm) {
            errorMsgObj.text(errorMsg);
            errorMsgObj.css("visibility", "visible");
        }
        var checkObj = $("#customCheckbox");
        var isChecked = checkObj.is(":checked");
        if (isChecked) {
            storageData();
        } else {
            clearStorageData();
        }
        if (isSubmitForm) {
            loginFormObj.submit();
        }
    }
    $("#loginBtn").click(function() {
        login();
    });
    function storageData() {
        var storage = window.localStorage;
        var userName = $("#userName").val();
        var password = $("#password").val();
        storage.setItem("userName", userName);
        storage.setItem("password", password);
    }
    function clearStorageData() {
        var storage = window.localStorage;
        storage.removeItem("userName");
        storage.removeItem("password");
    }
});