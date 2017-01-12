function getProjectName(){
    //Get theURL，eg： http://localhost:8083/uimcardprj/share/meun.jsp
    var curWwwPath=window.document.location.href;
    //Get the server's index，eg： uimcardprj/share/meun.jsp
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    //Get the server address，eg： http://localhost:8083
    var localhostPaht=curWwwPath.substring(0,pos);
    //Get the"/" project name，eg：/uimcardprj
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    return(projectName);
}
$(function() {
    $("#logout").click(function() {
        var storage = window.localStorage;
        var username = storage.getItem("username");
        var password = storage.getItem("password");
        if (username && password) {
            storage.removeItem("username");
            storage.removeItem("password");
        }
        var projectName = getProjectName();
        location.href = projectName+"/logout.action";
    });
});