/**
 * 변수선언
 */

let selectVal;

//로딩시 지난주 당첨번호 불러오기(저장 필요 없음)
$(document).ready(function(){
    console.log('로딩 성공');
});

function showInputBox() {
    console.log('typeSelect 성공');

    selectVal = $('#selectType').val();
    if (selectVal == 'selectNum' || selectVal == 'allSelect') {
        console.log('selectNum, allSelect');
        render(selectVal);
    } else {
        removeRender(selectVal);
    }

}

function render(selectVal) {
    console.log('inputBox 생성');
    $('#showInputBox')
        .html('<input type="text" name="inputNum" value="" placeholder="\',\'를 포함한 1~45 사이의 번호를 입력해주세요">');

    selectVal.remove();
}

function removeRender(selectVal) {
    $('#showInputBox')
        .html('');

    selectVal.remove();
}




