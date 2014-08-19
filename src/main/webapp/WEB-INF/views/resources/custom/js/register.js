/**
 * Created by root on 14-7-16.
 */


//jQuery.getJSON(url,[data],[callback])

$(document).ready(function () {

    var _AJAX = {
        success:"AJAX_SUCCESS",
        fail:"AJAX_FAIL"
    }

    var check = {
        isName : false,
        isEmail :false,
        isStudentNo : false,
        isPwd : false,
        isRePwd : false,
        isAcademy : false,
        isPro : false,
        isCellPhone : false
    };

    /*check the username*/
    $('#username').focus(function(){$('#username').css('color','#555555'); $('.regTips1').fadeOut()});
    $('#username').blur(function(){
        if(isNull($('#username').val())){
            $('#username').after("<span class='glyphicon glyphicon-remove-circle help-block regTips1' style='color:red'>姓名不能为空</span>");
            $('#username').parent().removeClass("has-success");
            check.isName = false;
        }else{
            $('#username').parent().addClass("has-success");
            check.isName = true;
        }
    });
    /*check the username end*/



    /*check the emial start*/
    $('#email').focus(function(){$('#email').css('color','#555555'); $('.regTips2').fadeOut()});
    $('#email').blur(function(){
        if(isEmail($('#email').val())){
            console.log("this is email");
            //ajax check the email isValid or not
            $.getJSON(
                '/user/checkEmail',
                {'email':$('#email').val()},
                function(json){
                    console.log("ajax back "+json);
                    if(json === _AJAX.success){
                        check.isEmail = true;
                        $('#email').parent().addClass("has-success");
                    }else{
                        check.isEmail = false;
                        $('#email').after("<span class='glyphicon glyphicon-remove-circle help-block regTips2' style='color:red'>该邮箱已经被使用</span>");
                        $('#email').parent().removeClass("has-success");
                    }
                }
            );
        }else{
            console.log("is not email");
            check.isEmail = false;
            $('#email').css('color','red');
            $('#email').after("<span class='glyphicon glyphicon-remove-circle help-block regTips2' style='color:red'>该邮箱无效，请重新输入</span>");
            $('#email').parent().removeClass("has-success");
        }
    });
    /*check the email end*/

    /*check the studentNo start*/
    $('#studentNo').focus(function(){$('#studentNo').css('color','#555555'); $('.regTips3').fadeOut()});
    $('#studentNo').blur(function(){
        if(isNumber($('#studentNo').val())){
            $('#studentNo').parent().addClass("has-success");
            check.isStudentNo = true;
        }else{
            $('#studentNo').after("<span class='glyphicon glyphicon-remove-circle help-block regTips3' style='color:red'>学号填写无效</span>");
            $('#studentNo').parent().removeClass("has-success");
            check.isStudentNo = false;
        }
    });
    /*check the studentNo end*/

    /*check the pwd start*/
    $('#pwd').focus(function(){$('#pwd').css('color','#555555'); $('.regTips4').fadeOut()});
    $('#pwd').blur(function(){
        var value = $('#pwd').val();
        if(value.length < 6 || value.length >20){
            $('#pwd').after("<span class='glyphicon glyphicon-remove-circle help-block regTips4' style='color:red'>密码长度有误</span>");
            $('#pwd').parent().removeClass("has-success");
            check.isStudentNo = false;
        }else{//如果密码长度匹配才继续判断 密码的组合 是否正确
            if(isNumberOrLetter($('#pwd').val())){
                console.log("this is pwd");
                $('#pwd').parent().addClass("has-success");
                check.isPwd = true;
            }else{
                console.log("is not pwd");
                $('#pwd').after("<span class='glyphicon glyphicon-remove-circle help-block regTips4' style='color:red'>密码格式不正确</span>");
                $('#pwd').parent().removeClass("has-success");
                check.isPwd = false;
            }
        }
    });
    /*check the pwd end*/

    /*check the repwd start*/
    $('#rePwd').focus(function(){$('#rePwd').css('color','#555555'); $('.regTips5').fadeOut()});
    $('#rePwd').blur(function(){
        if($('#rePwd').val() === $('#pwd').val()){
            console.log("this is rePwd");
            $('#rePwd').parent().addClass("has-success");
            check.isRePwd = true;
        }else{
            console.log("is not rePwd");
            $('#rePwd').after("<span class='glyphicon glyphicon-remove-circle help-block regTips5' style='color:red'>两次密码填写不一致</span>");
            $('#repwd').parent().removeClass("has-success");
            check.isRePwd = false;
        }
    });
    /*check the repwd end*/

    /*check the academy start*/
    $('#academy').focus(function(){$('#academy').css('color','#555555'); $('.regTips6').fadeOut()});
    $('#academy').blur(function(){
        if(isNull($('#academy').val())){
            $('#academy').after("<span class='glyphicon glyphicon-remove-circle help-block regTips6' style='color:red'>学院不能为空</span>");
            $('#academy').parent().removeClass("has-success");
            check.isAcademy = false;
        }else{
            $('#academy').parent().addClass("has-success");
            check.isAcademy = true;
        }
    });
    /*check the academy end*/

    /*check the pro start*/
    $('#pro').focus(function(){$('#pro').css('color','#555555'); $('.regTips7').fadeOut()});
    $('#pro').blur(function(){
        if(isNull($('#pro').val())){
            $('#pro').after("<span class='glyphicon glyphicon-remove-circle help-block regTips7' style='color:red'>专业不能为空</span>");
            $('#pro').parent().removeClass("has-success");
            check.isPro = false;
        }else{
            $('#pro').parent().addClass("has-success");
            check.isPro = true;
        }
    });
    /*check the pro end*/

    /*check the cellphone start*/
    $('#cellphone').focus(function(){$('#cellphone').css('color','#555555'); $('.regTips8').fadeOut()});
    $('#cellphone').blur(function(){
        if(!checkMobile($('#cellphone').val())){
            $('#cellphone').after("<span class='glyphicon glyphicon-remove-circle help-block regTips8' style='color:red'>手机号填写有误</span>");
            $('#cellphone').parent().removeClass("has-success");
            check.isCellPhone = false;
        }else{
            $('#cellphone').parent().addClass("has-success");
            check.isCellPhone = true;
        }
    });
    /*check the cellphone end*/

    $('#regSubmit').click(function(){
        if(check.isAcademy && check.isCellPhone && check.isEmail && check.isName && check.isPro && check.isPwd && check.isRePwd && check.isStudentNo){
            $('#regForm').submit();
        }else{
            $('#regSubmit').after("<span class='glyphicon glyphicon-remove-circle help-block regTips9' style='color:red'>资料尚未填写完毕</span>");
        }
        setTimeout(function(){$('.regTips9').fadeOut()},3000);
    });
});
