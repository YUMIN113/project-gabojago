//const reportForm = document.getElementsByClassName('modal-report-form')[0];
//const reportBtn = document.getElementsByClassName('modal-report-form-submitBtn')[0];
//let rsn1 = document.getElementsByClassName('modal-report-rsn1')[0];
//let rsn2 = document.getElementsByClassName('modal-report-form-rsn2')[0];
//const selectBox = document.getElementsByClassName('modal-report-rsn1')[0]
//
//selectBox.addEventListener('change', () => {
//  rsn1.value = selectBox.value;
//});
//
//reportBtn.addEventListener('click', () => {
//console.log("rsn1: " + rsn1.value);
//console.log("rsn2: " + rsn2.value);
//  fetch('recommendationReport', {
//    method: "POST",
//    headers: {
//        "Content-Type": "application/json",
//    },
//    body: JSON.stringify({
//      "rsn1": "rsn1.value",
//      "rsn2": rsn2.value
//    })
//  }).then(res => res.json()).then(data => {
//    if (data === true) {
//      alert('신고접수가 완료되었습니다.');
//      window.location.href = 'recommendationList';
//    } else {
//      alert('현재게시글은 신고할 수 없습니다.');
//    }
//  }).catch(err => console.log(err));
//});