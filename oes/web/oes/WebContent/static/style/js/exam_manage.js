$(function() {
    verifyCurrentOrder(currentOrder);
    $("#search").click(function() {
        var keywordLength = $("#keyword").val();
        if (keywordLength.length > 10) {
            $("#tipFlashMsg").text("Keyword's length should in 1~10.");
            $("#tipFlashMsg").css({"display" : "block"});
            closeTipFlashMsgWindow();
            return;
        }
        $("#searchForm").submit();
    });
    $(".orderArrow, .orderArrowInverse").click(function() {
        $("#hiddenOrderParam").val($(this).attr("id"));
        var currentOrderArrow = "";
        if ($(this).prop("class") === "orderArrow") {
            currentOrder = "ASC";
        } else {
            currentOrder = "DESC";
        }
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
        var currentOrderArrow = "";
        if (orderParam === "id") {
            currentOrderArrow = ".exam_id label";
        } else if(orderParam === "exam_name") {
            currentOrderArrow = ".exam_name label";
        } else {
            currentOrderArrow = ".exam_effective_time label";
        }
        if (currentOrder === "ASC") {
            $(currentOrderArrow).prop("class", "orderArrow");
        } else {
            $(currentOrderArrow).prop("class", "orderArrowInverse");
        }
    }
    $("#pageSize").change(function() {
        var pageSize = $(this).val();
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
    $(".pagination a").click(function() {
        $("#hiddenPage").val($(this).attr("id"));
        $("#searchForm").submit();
    });
    $("#dateButton").click(function() {
        var startDate = $("#startDate");
        var endDate = $("#endDate");
        if (new Date(startDate.val()) > new Date(endDate.val())) {
            $("#tipFlashMsg").text("The start date must less than endDate!");
            $("#tipFlashMsg").css({"display" : "block"});
            closeTipFlashMsgWindow();
            startDate.val("");
            endDate.val("");
            return;
        }
        $("#searchForm").submit();
    });
    $("#allCheckedCheckbox").click(function() {
        if ($(this).is(":checked")) {
            $("input[name='examCheckBox']").prop("checked", true);
        } else {
            $("input[name='examCheckBox']").prop("checked", false);
        }
    });
    $("input[name='examCheckBox']").click(function() {
        var notCheckedCount = $("input[name='examCheckBox']").not(":checked").size();
        if (notCheckedCount > 0) {
            $("#allCheckedCheckbox").prop("checked", false);
        } else {
            $("#allCheckedCheckbox").prop("checked", true);
        }
    });
    $("#closeBtn, #noBtn").click(function() {
        $("#examDeleteDialog").css("display", "none");
    });
    $("#cancel").click(function() {
        location.href = page + "/page/exam/manageExam";
    });
    $("#yesBtn").click(function() {
        $("#deleteForm").submit();
    });
    $("#deleteBtn").click(function() {
        var count = 0;
        $("input[name='examCheckBox']").each(function() {
            if ($(this).is(":checked")) {
                count++;
            }
        });
        if (count == 0) {
            $("#tipFlashMsg").text("You must check one checkBox!");
            $("#tipFlashMsg").css({"display" : "block"});
            closeTipFlashMsgWindow();
            return;
        }
        $("#examDeleteDialog").css("display", "block");
    });
});