/**
 * Created by JOHN on 24/02/15.
 */
$(function() {


//Make charts responsive
    Chart.defaults.global.responsive = true;
//get context only for the first chart found by jq
    var ctxCam = $("#myChartCam").get(0).getContext("2d");
// This will get the first returned node in the jQuery collection.
    var chartCam = new Chart(ctxCam);

    var ctxBar = $("#myChartBar").get(0).getContext("2d");
    var chartBar = new Chart(ctxBar);



    var optionsBar = {
        //Boolean - Whether the scale should start at zero, or an order of magnitude down from the lowest value
        scaleBeginAtZero: true,
        //Boolean - Whether grid lines are shown across the chart
        scaleShowGridLines: true,
        //String - Colour of the grid lines
        scaleGridLineColor: "rgba(0,0,0,.05)",
        //Number - Width of the grid lines
        scaleGridLineWidth: 1,
        //Boolean - Whether to show horizontal lines (except X axis)
        scaleShowHorizontalLines: true,
        //Boolean - Whether to show vertical lines (except Y axis)
        scaleShowVerticalLines: true,
        //Boolean - If there is a stroke on each bar
        barShowStroke: true,
        //Number - Pixel width of the bar stroke
        barStrokeWidth: 2,
        //Number - Spacing between each of the X value sets
        barValueSpacing: 5,
        //Number - Spacing between data sets within X values
        barDatasetSpacing: 1

    };

    var dataPie = [
        {
            'value': 300,
            'color': "#F7464A",
            'highlight': "#FF5A5E",
            'label': "Red"
        },
        {
            'value': 50,
            'color': "#46BFBD",
            'highlight': "#5AD3D1",
            'label': "Green"
        },
        {
            'value': 100,
            'color': "#FDB45C",
            'highlight': "#FFC870",
            'label': "Yellow"
        }
    ];

    var dataBar = {
        labels: ["January", "February", "March", "April", "May", "June", "July"],
        datasets: [
            {
                label: "My First dataset",
                fillColor: "rgba(220,220,220,0.2)",
                strokeColor: "rgba(220,220,220,1)",
                pointColor: "rgba(220,220,220,1)",
                pointStrokeColor: "#fff",
                pointHighlightFill: "#fff",
                pointHighlightStroke: "rgba(220,220,220,1)",
                data: [65, 59, 80, 81, 56, 55, 40]
            },
            {
                label: "My Second dataset",
                fillColor: "rgba(151,187,205,0.2)",
                strokeColor: "rgba(151,187,205,1)",
                pointColor: "rgba(151,187,205,1)",
                pointStrokeColor: "#fff",
                pointHighlightFill: "#fff",
                pointHighlightStroke: "rgba(151,187,205,1)",
                data: [28, 48, 40, 19, 86, 27, 90]
            }
        ]
    };

    
    chartCam.Pie(dataPie);
    $.get("/JEE_Projet-war/webresources/tuveuxquoi", function(data) {
        chartBar.Bar(data, optionsBar);
    }).fail(function() {
        alert("error");
    });

});