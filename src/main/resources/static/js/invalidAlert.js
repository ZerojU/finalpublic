console.log("this is invalid-alert import check log");


const pageFinder = document.getElementById('pageFinder');
const precList = document.getElementById('precList');

function invalidInputAlert() {
    let currentPage = parseInt(pageFinder.value);
    console.log(currentPage);

    if (isNaN(currentPage) || currentPage < 1 || !Number(currentPage)) {
        alert('잘못된 페이지 입력입니다.');
        //currentPage = 1;
    }
    //pageFinder.value = currentPage;


}


document.getElementById('pageFinder').addEventListener('submit', function(event) {
  // Prevent the default form submission behavior
  event.preventDefault();

  // Get the input value
  var inputValue = document.getElementById('myInput').value;

  // Check if the input value meets your desired condition
  if (inputValue !== 'desiredValue') {
    alert('Input value does not meet the desired condition. Submission blocked.');
    return false; // Prevent form submission
  }

  // If the input value meets the desired condition, allow form submission
  // Optionally, you can remove the next line to allow submission without any condition
  this.submit();
});
