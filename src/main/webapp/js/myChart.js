$(document).ready(function () {

    // start initialization
    $("#tableInc").hide();
    $("#tableSpend").hide();

    $("#buildButton").click(function () {
        var date1 = $("#datepickerFrom").val();
        var date2 = $("#datepickerTo").val();
        var day1 = date1.split('-')[0].replace(/^0+/, '');
        var day2 = date2.split('-')[0].replace(/^0+/, '');
        var month1 = date1.split('-')[1].replace(/^0+/, '');
        var month2 = date2.split('-')[1].replace(/^0+/, '');
        var year1 = date1.split('-')[2];
        var year2 = date2.split('-')[2];

        var amount = 0;
        var change = null;

        var axis = [];

        if ((year2 - year1) > 1) {
            amount = year2 - year1 + 1;
            change = "year";
            for (year1; year1 < year2; year1++) {
                axis.push(year1);
            }
        } else if ((month2 - month1) > 1) {
            amount = month2 - month1 + 1;
            change = "month";
            for (month1; month1 < month2; month1++) {
                switch (parseInt(month1)) {
                    case 1:
                        axis.push("January");
                        break;
                    case 2:
                        axis.push("February");
                        break;
                    case 3:
                        axis.push("March");
                        break;
                    case 4:
                        axis.push("April");
                        break;
                    case 5:
                        axis.push("May");
                        break;
                    case 6:
                        axis.push("June");
                        break;
                    case 7:
                        axis.push("July");
                        break;
                    case 8:
                        axis.push("August");
                        break;
                    case 9:
                        axis.push("September");
                        break;
                    case 10:
                        axis.push("October");
                        break;
                    case 11:
                        axis.push("November");
                        break;
                    case 12:
                        axis.push("December");
                        break;
                    default:
                        break;
                }
            }
        } else {
            amount = day2 - day1 + 1;
            change = "day";
            for (day1; day1 <= day2; day1++) {
                axis.push(day1);
            }
        }

        var arrIncoming = [];
        var arrSpending = [];

        var dateInc = [];
        var dateSpend = [];

        $.ajax({// incoming
            type: 'GET',
            url: "historyIncoming.html",
            data: {"date1": date1,
                "date2": date2,
                "amount": amount,
                "change": change},
            contentType: "application/json; charset=UTF-8",
            dataType: 'json',
            success: function (response) {
                var i = 0;
                $.each(response, function () {
                    arrIncoming.push(response[i].sumInc);
                    dateInc.push(response[i].dateInc);
                    arrSpending.push(response[i].sumSpend);
                    dateSpend.push(response[i++].dateSpend);
                });
                showChart(arrIncoming, arrSpending, axis, dateInc, dateSpend);
            } // end success
        });
    }); // end buildButton
});
// find spend
function showChart(arrIncoming, arrSpending, axis, dateInc, dateSpend) {

    console.log(axis);

    var options = {
        ///Boolean - Whether grid lines are shown across the chart
        scaleShowGridLines: true,
        //String - Colour of the grid lines
        scaleGridLineColor: "rgba(0,0,0,.05)",
        //Number - Width of the grid lines
        scaleGridLineWidth: 1,
        //Boolean - Whether to show horizontal lines (except X axis)
        scaleShowHorizontalLines: true,
        //Boolean - Whether to show vertical lines (except Y axis)
        scaleShowVerticalLines: true,
        //Boolean - Whether the line is curved between points
        bezierCurve: true,
        //Number - Tension of the bezier curve between points
        bezierCurveTension: 0.4,
        //Boolean - Whether to show a dot for each point
        pointDot: true,
        //Number - Radius of each point dot in pixels
        pointDotRadius: 4,
        //Number - Pixel width of point dot stroke
        pointDotStrokeWidth: 1,
        //Number - amount extra to add to the radius to cater for hit detection outside the drawn point
        pointHitDetectionRadius: 20,
        //Boolean - Whether to show a stroke for datasets
        datasetStroke: true,
        //Number - Pixel width of dataset stroke
        datasetStrokeWidth: 2,
        //Boolean - Whether to fill the dataset with a colour
        datasetFill: true,
        // Boolean - Whether to show labels on the scale
        scaleShowLabels: true,
        //String - A legend template
        legendTemplate: "<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0; i<datasets.length; i++){%><li><span style=\"background-color:<%=datasets[i].strokeColor%>\"></span><%if(datasets[i].label){%><%=datasets[i].label%><%}%></li><%}%></ul>"
    };
    var data = {
        labels: axis,
        datasets: [
            {
                label: "Р”РѕС…РѕРґС‹",
                fillColor: "rgba(34,139,34,0.2)",
                strokeColor: "rgba(34,139,34,1)",
                pointColor: "rgba(34,139,34,1)",
                pointStrokeColor: "#fff",
                pointHighlightFill: "#fff",
                pointHighlightStroke: "rgba(34,139,34,1)",
                data: arrIncoming
            },
            {
                label: "Р Р°СЃС…РѕРґС‹",
                fillColor: "rgba(255,0,0,0.2)",
                strokeColor: "rgba(255,0,0,1)",
                pointColor: "rgba(255,0,0,1)",
                pointStrokeColor: "#fff",
                pointHighlightFill: "#fff",
                pointHighlightStroke: "rgba(255,0,0,1)",
                data: arrSpending
            }
        ]
    };
    var context = document.getElementById("myChart").getContext("2d");
    var myLineChart = new Chart(context).Line(data, options);
    myLineChart.update();

    // create table
    if (arrIncoming.length === 0)
        $("#tableInc").hide();
    else
        $("#tableInc").show();

    if (arrSpending.length === 0)
        $("#tableSpend").hide();
    else
        $("#tableSpend").show();

    var i = 0;
    var amountInc = 0;
    $("#tableInc tbody tr").remove();
    $.each(arrIncoming, function () {
        amountInc = amountInc + arrIncoming[i];
        var tableRow = $("<tr />");
        var tableColumn1 = $("<td />").text(dateInc[i]);
        var tableColumn2 = $("<td />").text(arrIncoming[i++]);
        tableRow.append(tableColumn1, tableColumn2);
        $("#tableInc").find("tbody:last").append(tableRow);
    });
    var tableRow = $("<tr />");
    var tableColumn1 = $("<td />").text("Итог:");
    var tableColumn2 = $("<td />").text(amountInc);
    tableRow.append(tableColumn1, tableColumn2);
    $("#tableInc").find("tbody:last").append(tableRow);

    var i = 0;
    var amountSpend = 0;
    $("#tableSpend tbody tr").remove();
    $.each(arrSpending, function () {
        amountSpend = amountSpend + arrSpending[i];
        var tableRow = $("<tr />");
        var tableColumn1 = $("<td />").text(dateSpend[i]);
        var tableColumn2 = $("<td />").text(arrSpending[i++]);
        tableRow.append(tableColumn1, tableColumn2);
        $("#tableSpend").find("tbody:last").append(tableRow);
    });
    var tableRow = $("<tr />");
    var tableColumn1 = $("<td />").text("Итог:");
    var tableColumn2 = $("<td />").text(amountSpend);
    tableRow.append(tableColumn1, tableColumn2);
    $("#tableSpend").find("tbody:last").append(tableRow);

}