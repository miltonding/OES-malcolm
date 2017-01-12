$(function() {
    $("#closeBtn, #noBtn").click(function() {
        $("#questionDialogDiv").css("display", "none");
    });
    $("#deleteBtn").click(function() {
        var isChecked = verifyIsChecked();
        if (!isChecked) {
            $("#tipFlashMsg").text("You must check one question!");
            $("#tipFlashMsg").css({"display" : "block"});
            closeTipFlashMsgWindow();
            return;
        }
        $("#questionDialogDiv").css("display", "block");
        return;
    });
    $("input[name='questionCheckbox']").each(function() {
        $(this).click(function() {
            var checkBoxsObj = $("input[name='questionCheckbox']");
            var count = 0;
            checkBoxsObj.each(function() {
                if ($(this).is(":checked")) {
                    count++;
                }
            });
            if (count == checkBoxsObj.length) {
                $("#allCheckedCheckbox").prop("checked", true);
            } else {
                $("#allCheckedCheckbox").prop("checked", false);
            }
        });
    });
    $("#allCheckedCheckbox").click(function() {
        if ($(this).is(":checked")) {
            $("input[name='questionCheckbox']").each(function() {
                $(this).prop("checked", true);
                console.log("true");
            });
        } else {
            $("input[name='questionCheckbox']").each(function() {
                console.log("false");
                $(this).prop("checked", false);
            });
        }
    });
    $("#searchImg").click(function() {
        var keywordLength = $("#keyword").val();
        if (keywordLength.length > 10) {
            $("#tipFlashMsg").text("Keyword's length should in 1~10.");
            $("#tipFlashMsg").css({"display" : "block"});
            closeTipFlashMsgWindow();
            return;
        }
        $("#searchForm").submit();;
    });
    function verifyIsChecked() {
        var checkBoxsObj = document.getElementsByName("questionCheckbox");
        var isChecked = false;
        for (var i = 0; i < checkBoxsObj.length; i++) {
            if (checkBoxsObj[i].checked) {
                isChecked = true;
            }
        }
        return isChecked;
    }
    $("#yesBtn").click(function(){
        $("#deleteForm").submit();
    });
    verifyCurrentOrder(currentOrder);
    $("#pageSize").change(function() {
        var pageSize = $(this).val();
        $("#hiddenPage").val("1");
        $("#hiddenPageSize").val(pageSize);
        $("#searchForm").submit();
    });
    $("#goPage").click(function() {
        var pageSize = $("#pageSize").val();
        var page = $("#inputPage").val();
        var reg = /^\d+$/;
        if(!reg.test(page)){
            $("#tipFlashMsg").text("The parameter please input a int number");
            $("#tipFlashMsg").css({"display" : "block"});
            closeTipFlashMsgWindow();
            $("#inputPage").val("");
            return;
        }
        if (!page) {
            page = "1";
        }
        $("#hiddenCurrentOrder").val(currentOrder);
        $("#hiddenPage").val(page);
        $("#hiddenPageSize").val(pageSize);
        $("#searchForm").submit();
    });
    $("#orderArrow").click(function() {
        if (currentOrder === "") {
            currentOrder = "ASC";
        }
        if (currentOrder === "ASC") {
            $("#hiddenCurrentOrder").val("DESC");
            $("#hiddenPage").val("1");
            $("#hiddenPageSize").val(pageSizeByEl);
            $("#searchForm").submit();
        } else {
            $("#hiddenCurrentOrder").val("ASC");
            $("#hiddenPage").val("1");
            $("#hiddenPageSize").val(pageSizeByEl);
            $("#searchForm").submit();
        }
    });
    function verifyCurrentOrder(currentOrder) {
        if (currentOrder === "") {
            currentOrder = "ASC";
        }
        if (currentOrder === "ASC") {
            $("#orderArrow").css({"background-image" : "url('" + staticUtl + "/img/ICN_Decrese_10x15.png')"});
        } else {
            $("#orderArrow").css({"background-image" : "url('" + staticUtl + "/img/ICN_Increase_10x15.png')"});
        }
    }
    $(".pagination a").click(function() {
        $("#hiddenPage").val($(this).attr("id"));
        $("#searchForm").submit();
    });
});