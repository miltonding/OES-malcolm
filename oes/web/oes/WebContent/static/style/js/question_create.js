// The varible judge the form could sumbit or not.
var isSubmit = true;
$(function() {
     $("input[name='answer']").each(function() {
        $(this).click(function() {
            clean();
            $(this).parent().siblings("input").css({"background-color":"#D2DAE3"});
        });
    });
    $("#questionIdInput").blur(function() {
        verifyQuestionIdByCreate();
    });
    $("#cancelBtn").click(function() {
        cancel();
    });
    $("#saveBtn").click(function() {
        isSubmit = true;
        var checkedCount = 0;
        $("input[type='radio']").each(function() {
            if ($(this).is(":checked")) {
                checkedCount++;
            }
        });
        if (checkedCount != 1) {
            $("#tipFlashMsg").text("You must clicked one option!");
            $("#tipFlashMsg").css({"display" : "block"});
            closeTipFlashMsgWindow();
            return;
        }
        verifyQuestionOperation();
        if (isSubmit) {
            $("#saveForm").submit();
        }
    });
    $(".question_content_div input, textarea").blur(function() {
        verifyQuestionOperation();
    });
});
function isRepeat(answerOption) {
    var isRepeat = false;
    $(".inner_ul input[type='text']").each(function() {
        if(answerOption.val().trim() === $(this).val().trim()) {
            if(answerOption.attr("id") !== $(this).attr("id")) {
                isRepeat = true;
                isSubmit = false;
                $(this).css({"display" : "inline-block" });
                answerOption.css({"display" : "inline-block" });
                $(this).css({"border" : "dashed 1px red"});
                answerOption.css({"border" : "dashed 1px red"});
            }
        }
    });
    if (isRepeat) {
        $("#tipFlashMsg").text("You should input the different value for every option!");
        $("#tipFlashMsg").css({"display" : "block"});
        closeTipFlashMsgWindow();
        return true;
    }
    return false;
}
function verifyQuestionIdByCreate() {
    var questionId = $("#questionIdInput").val();
    if (questionId) {
        $.ajax({
            url: page + "/page/question/verifyQuestionIdByCreate",
            type: "POST",
            dataType: "text",
            data: {"questionId" : questionId},
            success: function(data) {
                if (data.length > 0 && data.length < 25) {
                    $("#tipFlashMsg").text(data);
                    $("#tipFlashMsg").css({"display" : "block"});
                    closeTipFlashMsgWindow();
                    $("#questionIdInput").css({"border" : "dashed 1px red"});
                    isSubmit = false;
                } else if (data.length > 25) {
                    $("#tipFlashMsg").text("You lost the connect with server,Please login again!");
                    $("#tipFlashMsg").css({"display" : "block"});
                    closeTipFlashMsgWindow();
                    isSubmit = false;
                } else {
                    $("#questionIdInput").css({"border" : "solid 1px #2E4358"});
                    $("#questionIdMsg").css({"display" : "none" });
                    $("#questionIdInput").val($("#questionIdInput").val().trim());
                    isSubmit = true;
                }
            },
            error: function() {
                $("#tipFlashMsg").text("You could not connect to server!");
                $("#tipFlashMsg").css({"display" : "block"});
                closeTipFlashMsgWindow();
                return;
            }
        });
    } else {
        return;
    }
}
function clean() {
    $("#answerA").css({"background-color":"#FFFFFF"});
    $("#answerB").css({"background-color":"#FFFFFF"});
    $("#answerC").css({"background-color":"#FFFFFF"});
    $("#answerD").css({"background-color":"#FFFFFF"});
}
function verifyQuestionOperation() {
    // Verify the question id.
    var questionIdValue = $("#questionIdInput");
    if (questionIdValue.val().trim().length < 1 || questionIdValue.val().trim().length > 20) {
        $("#questionIdMsg").css({"display" : "inline-block" });
        $("#questionIdInput").css({"border" : "dashed 1px red"});
        isSubmit = false;
    } else {
        $("#questionIdMsg").css({"display" : "none" });
        verifyQuestionIdByCreate();
        questionIdValue.val(questionIdValue.val().trim());
    }
    // Verify the question.
    var questionTextareaValue = $("#questionTextarea");
    if (questionTextareaValue.val().trim().length < 1 || questionTextareaValue.val().trim().length > 200) {
        $("#questionMsg").css({"display" : "inline-block" });
        $("#questionTextarea").css({"border" : "dashed 1px red"});
        isSubmit = false;
    } else {
        $("#questionTextarea").css({"border" : "solid 1px #2E4358"});
        $("#questionMsg").css({"display" : "none"});
        questionTextareaValue.val(questionTextareaValue.val().trim());
    }
    // Verify the answerA
    var answerAValue = $("#answerA");
    if (answerAValue.val().trim().length < 1 || answerAValue.val().trim().length > 30) {
        $("#answerAMsg").css({"display" : "inline-block" });
        $("#answerA").css({"border" : "dashed 1px red"});
        isSubmit = false;
    } else {
        if (isRepeat(answerAValue)) {
            return;
        }
        $("#answerA").css({"border" : "solid 1px #2E4358"});
        $("#answerAMsg").css({"display" : "none"});
        answerAValue.val(answerAValue.val().trim());
    }
    // Verify the answerB.
    var answerBValue = $("#answerB");
    if (answerBValue.val().trim().length < 1 || answerBValue.val().trim().length > 30) {
        $("#answerBMsg").css({"display" : "inline-block" });
        $("#answerB").css({"border" : "dashed 1px red"});
        isSubmit = false;
    } else {
        if (isRepeat(answerBValue)) {
            return;
        }
        $("#answerB").css({"border" : "solid 1px #2E4358"});
        $("#answerBMsg").css({"display" : "none"});
        answerBValue.val(answerBValue.val().trim());
    }
    // Verify the answerC.
    var answerCValue = $("#answerC");
    if (answerCValue.val().trim().length < 1 || answerCValue.val().trim().length > 30) {
        $("#answerCMsg").css({"display" : "inline-block" });
        $("#answerC").css({"border" : "dashed 1px red"});
        isSubmit = false;
    } else {
        if (isRepeat(answerCValue)) {
            return;
        }
        $("#answerC").css({"border" : "solid 1px #2E4358"});
        $("#answerCMsg").css({"display" : "none"});
        answerCValue.val(answerCValue.val().trim());
    }
    // Verify the answerD.
    var answerDValue = $("#answerD");
    if (answerDValue.val().trim().length < 1 || answerDValue.val().trim().length > 30) {
        $("#answerDMsg").css({"display" : "inline-block" });
        $("#answerD").css({"border" : "dashed 1px red"});
        isSubmit = false;
    } else {
        if (isRepeat(answerDValue)) {
            return;
        }
        $("#answerD").css({"border" : "solid 1px #2E4358"});
        $("#answerDMsg").css({"display" : "none"});
        answerDValue.val(answerDValue.val().trim());
    }
}

function cancel() {
    location.href = page + '/page/dashBoard/myoes';
}