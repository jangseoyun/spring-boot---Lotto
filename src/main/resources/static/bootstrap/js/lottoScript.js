/**
 * 변수선언
 */

//로딩시 지난주 당첨번호 불러오기(저장 필요 없음)
$(document).ready(function(){
    console.log('로딩 성공');
    getLottoNum();
});

function getLottoNum(){
    console.log('getLottoNum 성공');
}

$('#selectNum').on('click', function (){
    console.log('반자동 클릭');

})

$('#allSelect').on('click', function (){
    console.log('전체수동 클릭');

})


