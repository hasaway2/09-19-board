function emptyAndPatternCheck(value, $target, pattern, emptyMessage, patternCheckFailMessage) {
	$target.text('');
	if(value=='') {
		$target.text(emptyMessage);
		return false;
	}	else if(pattern.test(value)==false) {
		$target.text(patternCheckFailMessage);
		return false;
	}
	return true;
}

async function usernameCheck() {
	const value = $('#username').val();
	const $msg = $('#username-msg');
	const pattern = /^[a-z0-9]{6,10}$/;
	const result = emptyAndPatternCheck(value, $msg, pattern, '아이디를 입력하세요', '아이디는 소문자와 숫자 6~10자입니다');
	if(result==false)
		return false;
	try {
		await $.ajax("/member/username/check?username=" + value);
		return true;
	} catch(err) {
		$msg.text('사용중인 아이디입니다');
		return false;
	}
}

function passwordCheck() {
	const value = $('#password').val();
	const $msg = $('#password-msg');
	const pattern = /^[A-Za-z0-9]{6,10}$/;
	return emptyAndPatternCheck(value, $msg, pattern, '비밀번호를 입력하세요', '비밀번호는 영숫자 6~10자입니다');
}

function password2Check() {
	const password = $('#password').val();
	const password2 = $('#password2').val();
	$('#password2-msg').text('');
	if(password2=='') {
		$('#password2-msg').text('비밀번호를 다시 입력하세요');
		return false;
	} else if(password!=password2) {
		$('#password2-msg').text('비밀번호가 일치하지 않습니다');
		return false;
	}		
	return true;
}

function emailCheck() {
	const value = $('#email').val();
	const $msg = $('#email-msg');
	const pattern = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
	return emptyAndPatternCheck(value, $msg, pattern, '이메일을 입력하세요', '이메일을 정확하게 입력하세요');
}