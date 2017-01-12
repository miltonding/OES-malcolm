$(function() {
    var examNameObj = $("#examName");
    var descriptionObj = $("#examDescription");
    var effectiveDateObj = $("#examDate");
    var effectiveTimeObj = $("#examTime");
    var durationObj = $("#examDuration");
    var questionQuantityObj = $("#questionQuantity");
    var questionPointObj = $("#questionPoint");
    var totalScoreObj = $("#totalScore");
    var passCriteriaObj = $("#passCriteria");
    var isSubmit = true;
    function verifyExamOperation() {
        isSubmit = true;
        if (examNameObj.val().trim().length < 1 || examNameObj.val().trim().length > 20) {
            $("#examNameMsg").css({"display" : "inline-block" });
            examNameObj.css({"border" : "dashed 1px red"});
            isSubmit = false;
        } else {
            examNameObj.css({"border" : "solid 1px #2E4358"});
            $("#examNameMsg").css({"display" : "none" });
            examNameObj.val(examNameObj.val().trim());
        }
        if (descriptionObj.val().trim().length < 0 || descriptionObj.val().trim().length > 200) {
            $("#examDescriptionMsg").css({"display" : "inline-block" });
            descriptionObj.css({"border" : "dashed 1px red"});
            isSubmit = false;
        } else {
            descriptionObj.css({"border" : "solid 1px #2E4358"});
            $("#examDescriptionMsg").css({"display" : "none" });
            descriptionObj.val(descriptionObj.val().trim());
        }
        if (!effectiveDateObj.val().trim()) {
            $("#examDateMsg").css({"display" : "inline-block" });
            effectiveDateObj.css({"border" : "dashed 1px red"});
            isSubmit = false;
        } else {
            effectiveDateObj.css({"border" : "solid 1px #2E4358"});
        }
        if (!effectiveTimeObj.val().trim()) {
            $("#examTimeMsg").css({"display" : "inline-block" });
            effectiveTimeObj.css({"border" : "dashed 1px red"});
            isSubmit = false;
        } else {
            effectiveTimeObj.css({"border" : "solid 1px #2E4358"});
        }
        if (effectiveTimeObj.val().trim() && effectiveDateObj.val().trim()) {
            $("#examDateMsg").css({"display" : "none" });
        }
        if (questionQuantityObj.val().trim().length < 1 || questionQuantityObj.val().trim().length > 3) {
            $("#questionQuantityMsg").css({"visibility" : "visible" });
            questionQuantityObj.css({"border" : "dashed 1px red"});
            isSubmit = false;
        } else {
            questionQuantityObj.css({"border" : "solid 1px #2E4358"});
            $("#questionQuantityMsg").css({"visibility" : "hidden" });
            questionQuantityObj.val(questionQuantityObj.val().trim());
        }
        if (questionPointObj.val().trim().length < 1 || questionPointObj.val().trim().length > 2) {
            $("#questionPointMsg").css({"visibility" : "visible" });
            questionPointObj.css({"border" : "dashed 1px red"});
            isSubmit = false;
        } else {
            questionPointObj.css({"border" : "solid 1px #2E4358"});
            $("#questionPointMsg").css({"visibility" : "hidden" });
            questionPointObj.val(questionPointObj.val().trim());
        }
        if (passCriteriaObj.val().trim().length < 1 || passCriteriaObj.val().trim().length > 5) {
            $("#passCriteriaMsg").css({"visibility" : "visible" });
            passCriteriaObj.css({"border" : "dashed 1px red"});
            isSubmit = false;
        } else {
            passCriteriaObj.css({"border" : "solid 1px #2E4358"});
            $("#passCriteriaMsg").css({"visibility" : "hidden" });
            passCriteriaObj.val(passCriteriaObj.val().trim());
        }

        if(questionPointObj.val() && questionQuantityObj.val()) {
            if (passCriteriaObj.val() > questionPointObj.val() * questionQuantityObj.val()) {
                $("#tipFlashMsg").text("Pass criteria should less than total point.");
                $("#tipFlashMsg").css({"display" : "block"});
                closeTipFlashMsgWindow();
                passCriteriaObj.val("");
                return;
            }
        }

        // Verify the question's quantity, point and criteria.
        var questionQuantityValue = questionQuantityObj.val();
        var questionPointValue = questionPointObj.val();
        var passCriteriaValue = passCriteriaObj.val();
        var reg = /^\d+$/;
        if (questionQuantityValue) {
            if(!reg.test(questionQuantityValue)){
                $("#questionQuantityMsg").css({"visibility" : "visible" });
                questionQuantityObj.css({"border" : "dashed 1px red"});
                $("#tipFlashMsg").text("Please input a int number for Question quantity.");
                $("#tipFlashMsg").css({"display" : "block"});
                closeTipFlashMsgWindow();
                questionQuantityObj.val("");
            } 
        }
        if (questionPointValue) {
            if (!reg.test(questionPointValue)) {
                $("#questionPointMsg").css({"visibility" : "visible" });
                questionPointObj.css({"border" : "dashed 1px red"});
                $("#tipFlashMsg").text("Please input a int number for Question Point.");
                $("#tipFlashMsg").css({"display" : "block"});
                closeTipFlashMsgWindow();
                questionPointObj.val("");
            } 
            if (questionPointValue.trim().length > 0) {
                totalScoreObj.val(questionPointValue * questionQuantityObj.val());
            }
        }
        if (passCriteriaValue) {
            if(!reg.test(passCriteriaValue)) {
                $("#tipFlashMsg").text("Please input a int number for Question criteria.");
                $("#tipFlashMsg").css({"display" : "block"});
                closeTipFlashMsgWindow();
                passCriteriaObj.val("");
                $("#passCriteriaMsg").css({"visibility" : "visible" });
                passCriteriaObj.css({"border" : "dashed 1px red"});
            }
        }

        if (effectiveDateObj.val() && effectiveTimeObj.val()) {
            var nowDate = new Date();
            var inputDate = new Date(effectiveDateObj.val() + " " + effectiveTimeObj.val());
            if (inputDate < nowDate) {
                $("#tipFlashMsg").text("Please input a day greater than today.");
                $("#tipFlashMsg").css({"display" : "block"});
                effectiveDateObj.css({"border" : "dashed 1px red"});
                effectiveTimeObj.css({"border" : "dashed 1px red"});
                effectiveDateObj.val("");
                effectiveTimeObj.val("");
                closeTipFlashMsgWindow();
                return;
            }
        }
        totalScoreObj.val(questionQuantityObj.val() * questionPointObj.val());
    };

    function verifyQuestionQuantity() {
        $.ajax({
            url: page + "/page/question/getQuestionQuantity",
            type: "POST",
            dataType: "text",
            success: function(data) {
                if (data.length < 10) {
                    if(parseInt(data) < parseInt(questionQuantityObj.val())) {
                        $("#presentQuestionQuantity").text(data);
                        $("#examWarnDialog").css("display", "block");
                    } else {
                        $("#isDraft").val("0");
                        $("#saveForm").submit();
                    }
                } else {
                    $("#tipFlashMsg").text("You lost the connect with server,Please login again.!");
                    $("#tipFlashMsg").css({"display" : "block"});
                    closeTipFlashMsgWindow();
                    return;
                }
            },
            error: function() {
                $("#tipFlashMsg").text("You could not connect to server!");
                $("#tipFlashMsg").css({"display" : "block"});
                closeTipFlashMsgWindow();
                return;
            }
        });
    }
    $("#saveExam").click(function() {
        verifyExamOperation();
        if (isSubmit) {
            verifyQuestionQuantity();
        }
    });
    $(".exam_content_div input, textarea").blur(function() {
        verifyExamOperation();
    });
    $("#closeBtn, #noBtn").click(function() {
        $("#examWarnDialog").css("display", "none");
    });
    $("#cancel").click(function() {
        location.href = page + "/page/exam/manageExam";
    });
    $("#yesBtn").click(function() {
        if (isSubmit) {
            $("#isDraft").val("1");
            $("#saveForm").submit();
        }
    });
});