$(document).ready(function () {
    showExchangeRates();
});

function saveEx() {
    hideExchangeRates();
    var USD = document.getElementById("USD").value;
    var EURO = document.getElementById("EURO").value;
    $.ajax({
        type: 'GET',
        encoding: "UTF-8",
        url: "saveExchange.html",
        data: {"USD": USD,
            "EURO": EURO},
        contentType: "application/json; charset=UTF-8",
        dataType: 'json',
        success: function (response) {
        }
    });
}

function showExchangeRates() {
    $("#popupExchange").show();
    $.ajax({
        type: 'GET',
        encoding: "UTF-8",
        url: "getExchange.html",
        contentType: "application/json; charset=UTF-8",
        dataType: 'json',
        success: function (response) {
            var i = 0;
            $.each(response, function () {
                if (response[i].name === "USD") {
                    $("#USD").val(response[i].value);
                } else if (response[i].name === "EURO") {
                    $("#EURO").val(response[i].value);
                }
                i++;
            });
        }
    });
}

function hideExchangeRates() {
    $("#popupExchange").hide();
}

