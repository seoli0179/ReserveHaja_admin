$(document).ready(function() {
    $("#submit").on('click', function(e) {
        e.preventDefault(); // 폼의 기본 제출 동작을 방지

        var adminId = $("#adminId").val();
        var adminPassword = $("#adminPassword").val();

        if(isId(adminId) && isPw(adminPassword)){
            join(adminId,adminPassword)
        }else{
            alert("4자리 이상 입력");
        }


    });
});

function isId(asValue) {
    var regExp = /^[a-z0-9]{3,19}$/g;
    return regExp.test(asValue);
}

function isPw(asValue) {
    var regExp = /^[a-z0-9]{3,19}$/g;
    return regExp.test(asValue);
}

function join(adminId,adminPassword) {
    $.ajax({
        url: '/auth/join', // 로그인 처리를 위한 서버의 URL
        type: 'POST',
        data: {
            adminId: adminId,
            adminPassword: adminPassword
        },
        success: function(response) {
            console.log('회원가입 ' + response);
            if(response==='SUCCESS')
                location.replace('/auth/login');
        },
        error: function(xhr, status, error) {
            console.log('회원가입 실패:', error);
        }
    });
}