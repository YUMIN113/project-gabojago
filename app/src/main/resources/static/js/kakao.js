let place_name_list = [];
let road_address_name_list = [];
let phone_list = [];
let x_list = [];
let y_list = [];

const doSearch1 = function () {
  place_name_list.splice(0);
  road_address_name_list.splice(0);
  phone_list.splice(0);
  x_list.splice(0);
  y_list.splice(0);

  const keyword = $(".ga-input-box1").val();

  if (keyword.length > 0) {
    $.ajax({
      url:
        "https://dapi.kakao.com/v2/local/search/keyword.json?size=5&query=" +
        encodeURIComponent(keyword),
      type: "GET",
      headers: {
        Authorization: "KakaoAK 0164bda1178748f8b8e6ff5f441710a0",
      },
      success: function (data) {
        for (let i = 0; i < data.documents.length; i++) {
          place_name_list.push(data.documents[i].place_name);
          road_address_name_list.push(data.documents[i].road_address_name);
          phone_list.push(data.documents[i].phone);
          x_list.push(data.documents[i].x);
          y_list.push(data.documents[i].y);
        }
        $("#searchOptions").html("");
        for (let i = 0; i < place_name_list.length; i++) {
          if (road_address_name_list[i].length >= 4) {
            $("#searchOptions").append(
              $("<option>").html(
                place_name_list[i] + ", " + road_address_name_list[i]
              )
            );
          } else if (road_address_name_list[i].length < 4) {
            $("#searchOptions").append($("<option>").html(place_name_list[i]));
          }
        }
      },
      error: function (e) {
        console.log(e);
      },
    });
  }
};
const doSearch2 = function () {
  place_name_list.splice(0);
  road_address_name_list.splice(0);
  phone_list.splice(0);
  x_list.splice(0);
  y_list.splice(0);

  const keyword = $(".ga-input-box2").val();

  if (keyword.length > 0) {
    $.ajax({
      url:
        "https://dapi.kakao.com/v2/local/search/keyword.json?size=5&query=" +
        encodeURIComponent(keyword),
      type: "GET",
      headers: {
        Authorization: "KakaoAK 0164bda1178748f8b8e6ff5f441710a0",
      },
      success: function (data) {
        for (let i = 0; i < data.documents.length; i++) {
          place_name_list.push(data.documents[i].place_name);
          road_address_name_list.push(data.documents[i].road_address_name);
          phone_list.push(data.documents[i].phone);
          x_list.push(data.documents[i].x);
          y_list.push(data.documents[i].y);
        }
        $("#searchOptions").html("");
        for (let i = 0; i < place_name_list.length; i++) {
          if (road_address_name_list[i].length >= 4) {
            $("#searchOptions").append(
              $("<option>").html(
                place_name_list[i] + ", " + road_address_name_list[i]
              )
            );
          } else if (road_address_name_list[i].length < 4) {
            $("#searchOptions").append($("<option>").html(place_name_list[i]));
          }
        }
      },
      error: function (e) {
        console.log(e);
      },
    });
  }
};
const doSearch3 = function () {
  place_name_list.splice(0);
  road_address_name_list.splice(0);
  phone_list.splice(0);
  x_list.splice(0);
  y_list.splice(0);

  const keyword = $(".ga-input-box3").val();

  if (keyword.length > 0) {
    $.ajax({
      url:
        "https://dapi.kakao.com/v2/local/search/keyword.json?size=5&query=" +
        encodeURIComponent(keyword),
      type: "GET",
      headers: {
        Authorization: "KakaoAK 0164bda1178748f8b8e6ff5f441710a0",
      },
      success: function (data) {
        for (let i = 0; i < data.documents.length; i++) {
          place_name_list.push(data.documents[i].place_name);
          road_address_name_list.push(data.documents[i].road_address_name);
          phone_list.push(data.documents[i].phone);
          x_list.push(data.documents[i].x);
          y_list.push(data.documents[i].y);
        }
        $("#searchOptions").html("");
        for (let i = 0; i < place_name_list.length; i++) {
          if (road_address_name_list[i].length >= 4) {
            $("#searchOptions").append(
              $("<option>").html(
                place_name_list[i] + ", " + road_address_name_list[i]
              )
            );
          } else if (road_address_name_list[i].length < 4) {
            $("#searchOptions").append($("<option>").html(place_name_list[i]));
          }
        }
      },
      error: function (e) {
        console.log(e);
      },
    });
  }
};
const doSearch4 = function () {
  place_name_list.splice(0);
  road_address_name_list.splice(0);
  phone_list.splice(0);
  x_list.splice(0);
  y_list.splice(0);

  const keyword = $(".ga-input-box4").val();

  if (keyword.length > 0) {
    $.ajax({
      url:
        "https://dapi.kakao.com/v2/local/search/keyword.json?size=5&query=" +
        encodeURIComponent(keyword),
      type: "GET",
      headers: {
        Authorization: "KakaoAK 0164bda1178748f8b8e6ff5f441710a0",
      },
      success: function (data) {
        for (let i = 0; i < data.documents.length; i++) {
          place_name_list.push(data.documents[i].place_name);
          road_address_name_list.push(data.documents[i].road_address_name);
          phone_list.push(data.documents[i].phone);
          x_list.push(data.documents[i].x);
          y_list.push(data.documents[i].y);
        }
        $("#searchOptions").html("");
        for (let i = 0; i < place_name_list.length; i++) {
          if (road_address_name_list[i].length >= 4) {
            $("#searchOptions").append(
              $("<option>").html(
                place_name_list[i] + ", " + road_address_name_list[i]
              )
            );
          } else if (road_address_name_list[i].length < 4) {
            $("#searchOptions").append($("<option>").html(place_name_list[i]));
          }
        }
      },
      error: function (e) {
        console.log(e);
      },
    });
  }
};
const doSearch5 = function () {
  place_name_list.splice(0);
  road_address_name_list.splice(0);
  phone_list.splice(0);
  x_list.splice(0);
  y_list.splice(0);

  const keyword = $(".ga-input-box5").val();

  if (keyword.length > 0) {
    $.ajax({
      url:
        "https://dapi.kakao.com/v2/local/search/keyword.json?size=5&query=" +
        encodeURIComponent(keyword),
      type: "GET",
      headers: {
        Authorization: "KakaoAK 0164bda1178748f8b8e6ff5f441710a0",
      },
      success: function (data) {
        for (let i = 0; i < data.documents.length; i++) {
          place_name_list.push(data.documents[i].place_name);
          road_address_name_list.push(data.documents[i].road_address_name);
          phone_list.push(data.documents[i].phone);
          x_list.push(data.documents[i].x);
          y_list.push(data.documents[i].y);
        }
        $("#searchOptions").html("");
        for (let i = 0; i < place_name_list.length; i++) {
          if (road_address_name_list[i].length >= 4) {
            $("#searchOptions").append(
              $("<option>").html(
                place_name_list[i] + ", " + road_address_name_list[i]
              )
            );
          } else if (road_address_name_list[i].length < 4) {
            $("#searchOptions").append($("<option>").html(place_name_list[i]));
          }
        }
      },
      error: function (e) {
        console.log(e);
      },
    });
  }
};

// fin_after_select 배열을 form submit 할 때 같이 보내기
let fin_before_select = "";
const fin_after_select = [];

const doAdd1 = function () {
  $(".ga-input-box1").attr("readonly", true);
  fin_after_select.push(fin_before_select);
  console.log(fin_after_select);
};
const doAdd2 = function () {
  $(".ga-input-box2").attr("readonly", true);
  fin_after_select.push(fin_before_select);
  console.log(fin_after_select);
};
const doAdd3 = function () {
  $(".ga-input-box3").attr("readonly", true);
  fin_after_select.push(fin_before_select);
  console.log(fin_after_select);
};
const doAdd4 = function () {
  $(".ga-input-box4").attr("readonly", true);
  fin_after_select.push(fin_before_select);
  console.log(fin_after_select);
};
const doAdd5 = function () {
  $(".ga-input-box5").attr("readonly", true);
  fin_after_select.push(fin_before_select);
  console.log(fin_after_select);
};

$(document).on("change", "input", function () {
  let options = $("datalist")[0].options;
  let val = $(this).val();
  fin_before_select = "";
  for (var i = 0; i < options.length; i++) {
    if (options[i].value === val) {
      fin_before_select =
        place_name_list[i] +
        "," +
        road_address_name_list[i] +
        "," +
        phone_list[i] +
        "," +
        x_list[i] +
        "," +
        y_list[i] +
        "//";
      break;
    }
  }
});

$("#place-add-btn1").click(doAdd1);
$("#place-add-btn2").click(doAdd2);
$("#place-add-btn3").click(doAdd3);
$("#place-add-btn4").click(doAdd4);
$("#place-add-btn5").click(doAdd5);
$(".ga-input-box1").keyup(doSearch1);
$(".ga-input-box2").keyup(doSearch2);
$(".ga-input-box3").keyup(doSearch3);
$(".ga-input-box4").keyup(doSearch4);
$(".ga-input-box5").keyup(doSearch5);

$("#ga-files1").on('change',function(){
  let fileName1 = document.getElementById("ga-files1").files;
  let fileName11 = "";
    for (let i = 0; i < fileName1.length; i++) {
      if (i === fileName1.length-1) {
        fileName11 += fileName1[i].name;
      } else {
        fileName11 += fileName1[i].name + ' / ';
      }
    }
      $(".ga-upload-namebox").eq(0).val(fileName11);
});
$("#ga-files2").on('change',function(){
  let fileName2 = document.getElementById("ga-files2").files;
  let fileName22 = "";
    for (let i = 0; i < fileName2.length; i++) {
      if (i === fileName2.length-1) {
        fileName22 += fileName2[i].name;
      } else {
        fileName22 += fileName2[i].name + ' / ';
      }
    }
      $(".ga-upload-namebox").eq(1).val(fileName22);
});
$("#ga-files3").on('change',function(){
  let fileName3 = document.getElementById("ga-files3").files;
  let fileName33 = "";
    for (let i = 0; i < fileName3.length; i++) {
      if (i === fileName3.length-1) {
        fileName33 += fileName3[i].name;
      } else {
        fileName33 += fileName3[i].name + ' / ';
      }
    }
      $(".ga-upload-namebox").eq(2).val(fileName33);
});
$("#ga-files4").on('change',function(){
  let fileName4 = document.getElementById("ga-files4").files;
  let fileName44 = "";
    for (let i = 0; i < fileName4.length; i++) {
      if (i === fileName4.length-1) {
        fileName44 += fileName4[i].name;
      } else {
        fileName44 += fileName4[i].name + ' / ';
      }
    }
      $(".ga-upload-namebox").eq(3).val(fileName44);
});
$("#ga-files5").on('change',function(){
  let fileName5 = document.getElementById("ga-files5").files;
  let fileName55 = "";
    for (let i = 0; i < fileName5.length; i++) {
      if (i === fileName5.length-1) {
        fileName55 += fileName5[i].name;
      } else {
        fileName55 += fileName5[i].name + ' / ';
      }
    }
      $(".ga-upload-namebox").eq(4).val(fileName55);
});

// enter키로 submit 방지
$('input[type="text"]').keydown(function() {
  if (event.keyCode === 13) {
    event.preventDefault();
  };
});