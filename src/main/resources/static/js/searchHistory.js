console.log("this is searchhistory import check log");

// 각 검색마다 검색어 목록은 최대 10개 까지만 받기로
// 배열에 각각 저장, !!!중복 없이!!! 로컬 스토리지에 쿼리 셀렉터를 통해
// 검색어를 가져올 변수 ------ (서비스)Query
// 검색어를 저장할 리스트 -------- (서비스)QueryList


//"   " 혹은 "" 을 체크하기 위해 받은 문자열을 줄여보는 함수 선언
function isWhitespace(str) {
    return str.trim() === "";
}

function writeQnAHistory() {
    console.log("function writeQnAHistory called");

    let qnaQuery = document.querySelector('#qna-query');
    let qnaQueryList = JSON.parse(localStorage.getItem('qnaList')) || [];

    if (!(isWhitespace(qnaQuery.value))) {
        if (! qnaQueryList.includes(qnaQuery.value)) { //중복이 없다면
            qnaQueryList.unshift(qnaQuery.value); // 맨 앞에 추가
        }
    }

    if (qnaQueryList.length > 10) {  //10개 이상일 때 맨 아래에서부터 지움
        qnaQueryList.pop();
    }
    localStorage.setItem('qnaList', JSON.stringify(qnaQueryList));
}


function writePrecHistory() {
    console.log("function writePrecHistory called");

    let precQuery = document.querySelector('#precedent-query');
    let precQueryList = JSON.parse(localStorage.getItem('precList')) || [];

    if (!(isWhitespace(precQuery.value))) {
        if (! precQueryList.includes(precQuery.value)) { //중복이 없다면
            precQueryList.unshift(precQuery.value);
        }
    }

    if (precQueryList.length > 10) {
        precQueryList.pop();
    }
    localStorage.setItem('precList', JSON.stringify(precQueryList));
}

function writeClauHistory() {
    console.log("function writeClauHistory called");

    let clauQuery = document.querySelector('#clause-query');
    let clauQueryList = JSON.parse(localStorage.getItem('clauList')) || [];

    if (!(isWhitespace(clauQuery.value))) {
        if (! clauQueryList.includes(clauQuery.value)) { //중복이 없다면
            clauQueryList.unshift(clauQuery.value);
        }
    }

    if (clauQueryList.length > 10) {
        clauQueryList.pop();
    }
    localStorage.setItem('clauList', JSON.stringify(clauQueryList));
}


