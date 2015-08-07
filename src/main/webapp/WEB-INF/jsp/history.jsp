<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>История</title>
        <script type="text/javascript" src="js/jquery-2.1.3.js"></script>
        <script type="text/javascript" src="js/Chart.js"></script>
        <script type="text/javascript" src="datepicker/jquery-ui.js"></script>

        <script type="text/javascript" src="js/myCalendar.js"></script>
        <script type="text/javascript" src="js/myChart.js"></script>

        <script type="text/javascript" src="js/exchangeRates.js"></script>

        <link rel="stylesheet" type="text/css" href="css/history.css" />
        <link rel="stylesheet" type="text/css" href="css/exchangeRates.css" />
        <link rel="stylesheet" href="datepicker/jquery-ui.css">

    </head>
    <body>

        <h3><a href="http://localhost:8080/FamilyFinances/index.html">На главную</a></h3>

        <div id="chartDiv"> 
            <h2>Выберите период от:</h2>
            <h2><input class="date" id="datepickerFrom" name="date1">
                до: <input class="date" id="datepickerTo" name="date2">
                <input type="button" id="buildButton" value="Построить">
            </h2>

            <canvas id="myChart"></canvas>
        </div>

        <div id="tableInc">
            <table>
                <h2 class="fontInc">Доходы:</h2>
                <thead>
                    <tr>
                        <th>Дата</th>
                        <th>Сумма</th>
                    </tr>
                </thead>
                <tbody>
                    <!--<tr onclick="$(this).addClass('highlight')"> </tr>-->
                </tbody>
            </table>
        </div>

        <div id="tableSpend">
            <table>
                <h2 class="fontSpend">Расходы:</h2>
                <thead>
                    <tr>
                        <th>Дата</th>
                        <th>Сумма</th>
                    </tr>
                </thead>
                <tbody>
                    <!--<tr onclick="$(this).addClass('highlight')"> </tr>-->
                </tbody>
            </table>
        </div>

        <!-- Exchange Rates -->
        <div class="b-popupExchange" id="popupExchange">
            <div class="b-popup-content">
                <h1>Курс валют</h1>
                <form>
                    <p>USD: &nbsp; &nbsp; <input type="text" id="USD" size="10">  </p>
                    <p>EURO: &nbsp; <input type="text" id="EURO" size="10"> </p>

                    <p> <input type="button" id="saveExchange" onclick="saveEx()" value="Сохранить">
                        <input type="reset" id="deleteExchange" value="удалить"></p>
                </form>
                <a href="javascript:hideExchangeRates()">Скрыть</a>
            </div>
        </div>

    </body>
</html>