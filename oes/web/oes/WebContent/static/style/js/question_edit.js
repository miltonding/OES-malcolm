$(function() {
    // The varible judge the form could sumbit or not.
    var isSubmit = true;
    var isChanged = false;
    var oldQuestionIdValue = $("#questionIdInput").val();
    var oldQuestionTextareaValue = $("#questionTextarea").val();
    var oldAnswerAValue = $("#answerA").val();
    var oldAnswerBValue = $("#answerB").val();
    var oldAnswerCValue = $("#answerC").val();
    var oldAnswerDValue = $("#answerD").val();

    var saveFormObj = $("#saveForm");
    var questionIdValue = $("#questionIdInput");
    var questionTextareaValue = $("#questionTextarea");
    var answerBValue = $("#answerB");
    var answerAValue = $("#answerA");
    var answerCValue = $("#answerC");
    var answerDValue = $("#answerD");
    $("input[name='answer']").each(function() {
        $(this).click(function() {
            clean();
            $(this).parent().siblings("input").css({"background-color":"#D2DAE3"});
        });
    });
    $("#questionIdInput").blur(function() {
        verifyQuestionIdByEdit();
    });
    function clean() {
        $("#answerA").css({"background-color":"#FFFFFF"});
        $("#answerB").css({"background-color":"#FFFFFF"});
        $("#answerC").css({"background-color":"#FFFFFF"});
        $("#answerD").css({"background-color":"#FFFFFF"});
    }
    $(".question_content_div input, textarea").blur(function() {
        verifyQuestionOperation();
        verifyIsChangedQuestion
    });
    $("#saveQuestionBtn").click(function() {
        verifyQuestionOperation();
        verifyIsChangedQuestion();
        if (isSubmit && isChanged) {
            saveFormObj.submit();
        }
    });
    function verifyIsChangedQuestion() {
        isChanged = false;
        if (questionIdValue.val() != oldQuestionIdValue) {
            isChanged = true;
        }
        if (questionTextareaValue.val() != oldQuestionTextareaValue) {
            isChanged = true;
        }
        if (answerAValue.val() != oldAnswerAValue) {
            isChanged = true;
        }
        if (answerBValue.val() != oldAnswerBValue) {
            isChanged = true;
        }
        if (answerCValue.val() != oldAnswerCValue) {
            isChanged = true;
        }
        if (answerDValue.val() != oldAnswerDValue) {
            isChanged = true;
        }
        $("input[name = 'answer']").each(function() {
            if ($(this).is(":checked")) {
                if($(this).val() != questionAnswer) {
                    isChanged = true;
                }
            }
        });
        if (!isChanged) {
            $("#tipFlashMsg").text("You don't change any item.");
            $("#tipFlashMsg").css({"display" : "block"});
            closeTipFlashMsgWindow();
            return;
        }
    }
    function verifyQuestionOperation() {
        isSubmit = true;
        // Verify the question id.
        if (questionIdValue.val().trim().length < 1 || questionIdValue.val().trim().length > 20) {
            $("#questionIdMsg").css({"display" : "inline-block"});
            $("#questionIdInput").css({"border" : "dashed 1px red"});
            isSubmit = false;
        } else {
            $("#questionIdInput").css({"border" : "solid 1px #2E4358"});
            verifyQuestionIdByEdit();
            questionIdValue.val(questionIdValue.val().trim());
        }
        // Verify the question.
        if (questionTextareaValue.val().trim().length < 1 || questionTextareaValue.val().trim().length > 200) {
            $("#questionMsg").css({"display" : "inline-block"});
            $("#questionTextarea").css({"border" : "dashed 1px red"});
            isSubmit = false;
        } else {
            $("#questionTextarea").css({"border" : "solid 1px #2E4358"});
            $("#questionMsg").css({"display" : "none"});
            questionTextareaValue.val(questionTextareaValue.val().trim());
        }
        // Verify the answerA
        if (answerAValue.val().trim().length < 1 || answerAValue.val().trim().length > 30) {
            $("#answerAMsg").css({"display" : "inline-block"});
            $("#answerA").css({"border" : "dashed 1px red"});
            isSubmit = false;
        } else {
            if (isRepeat(answerAValue)) {
                isSubmit = false;
                return;
            }
            $("#answerA").css({"border" : "solid 1px #2E4358"});
            $("#answerAMsg").css({"display" : "none"});
            answerAValue.val(answerAValue.val().trim());
        }
        // Verify the answerB.
        if (answerBValue.val().trim().length < 1 || answerBValue.val().trim().length > 30) {
            $("#answerBMsg").css({"display" : "inline-block"});
            $("#answerB").css({"border" : "dashed 1px red"});
            isSubmit = false;
        } else {
            if (isRepeat(answerBValue)) {
                isSubmit = false;
                return;
            }
            $("#answerB").css({"border" : "solid 1px #2E4358"});
            $("#answerBMsg").css({"display" : "none"});
            answerBValue.val(answerBValue.val().trim());
        }
        // Verify the answerC.
        if (answerCValue.val().trim().length < 1 || answerCValue.val().trim().length > 30) {
            $("#answerCMsg").css({"display" : "inline-block"});
            $("#answerC").css({"border" : "dashed 1px red"});
            isSubmit = false;
        } else {
            if (isRepeat(answerCValue)) {
                isSubmit = false;
                return;
            }
            $("#answerC").css({"border" : "solid 1px #2E4358"});
            $("#answerCMsg").css({"display" : "none"});
            answerCValue.val(answerCValue.val().trim());
        }
        // Verify the answerD.
        if (answerDValue.val().trim().length < 1 || answerDValue.val().trim().length > 30) {
            $("#answerDMsg").css({"display" : "inline-block"});
            $("#answerD").css({"border" : "dashed 1px red"});
        } else {
            if (isRepeat(answerDValue)) {
                isSubmit = false;
                return;
            }
            $("#answerD").css({"border" : "solid 1px #2E4358"});
            $("#answerDMsg").css({"display" : "none"});
            answerDValue.val(answerDValue.val().trim());
        }
    };
    $("#cancleBtn").click(function () {
        location.href = page + '/page/dashBoard/myoes';
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
            isSubmit = false;
            return true;
        }
        return false;
    }
    function verifyQuestionIdByEdit() {
        var questionId = $("#questionIdInput").val();
        if (questionId) {
            $.ajax({
                url: page + "/page/question/verifyQuestionIdByEdit",
                type: "POST",
                dataType: "text",
                data: {"questionId" : questionId, "oldQuestionId" : oldQuestionId},
                success: function(data) {
                    if (data.length > 0 && data.length < 25) {
                        $("#tipFlashMsg").text(data);
                        $("#tipFlashMsg").css({"display" : "block"});
                        closeTipFlashMsgWindow();
                        $("#questionIdInput").css({"border" : "dashed 1px red"});
                        isSubmit = false;
                        return;
                    } else if (data.length > 25) {
                        $("#tipFlashMsg").text("You could not connect to server!");
                        $("#tipFlashMsg").css({"display" : "block"});
                        closeTipFlashMsgWindow();
                        isSubmit = false;
                        return;
                    } else {
                        $("#questionIdInput").css({"border" : "solid 1px #2E4358"});
                        $("#questionIdMsg").css({"display" : "none" });
                        $("#questionIdInput").val($("#questionIdInput").val().trim());
                        isSubmit = true;
                        return;
                    }
                },
                error: function() {
                    $("#tipFlashMsg").text("You could not connect to server!");
                    $("#tipFlashMsg").css({"display" : "block"});
                    closeTipFlashMsgWindow();
                }
            });
        } else {
            return;
        }
    }
});