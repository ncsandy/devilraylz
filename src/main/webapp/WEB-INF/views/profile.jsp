<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="apple-touch-icon" sizes="76x76" href="${pageContext.request.contextPath}/static/assets/img/apple-icon.png">
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/static/assets/img/favicon.png">
    <title>
        Devilray LZ
    </title>
    <!--     Fonts and icons     -->
    <link href="https://fonts.googleapis.com/css?family=Poppins:200,300,400,600,700,800" rel="stylesheet" />
    <link href="https://use.fontawesome.com/releases/v5.0.6/css/all.css" rel="stylesheet">
    <!-- Nucleo Icons -->
    <link href="${pageContext.request.contextPath}/static/assets/css/nucleo-icons.css" rel="stylesheet" />
    <!-- CSS Files -->
    <link href="${pageContext.request.contextPath}/static/assets/css/black-dashboard.css?v=1.0.0" rel="stylesheet" />
    <!-- CSS Just for demo purpose, don't include it in your project -->
    <link href="${pageContext.request.contextPath}/static/assets/demo/demo.css" rel="stylesheet" />
</head>

<body class="">

<div class="wrapper">
    <div class="sidebar">
        <!--
          Tip 1: You can change the color of the sidebar using: data-color="blue | green | orange | red"
      -->
        <div class="sidebar-wrapper">
            <div class="logo">
                <a href="javascript:void(0)" class="simple-text logo-mini">
                   ${user_account.pilotRank}
                </a>
                <a href="javascript:void(0)" class="simple-text logo-normal">
                    ${user_account.fName} ${user_account.lName}
                </a>
            </div>
            <ul class="nav">
                <li class="active ">
                    <a href="#">
                        <i class="tim-icons icon-chart-pie-36"></i>
                        <p>Dashboard</p>
                    </a>
                </li>
                <li>
                    <a href="currency">
                        <i class="tim-icons icon-time-alarm"></i>
                        <p>Currency</p>
                    </a>
                </li>
                <li>
                    <a href="publications">
                        <i class="tim-icons icon-paper"></i>
                        <p>Publications</p>
                    </a>
                </li>
                <li>
                    <a href="minimums">
                        <i class="tim-icons icon-single-02"></i>
                        <p>Pilot Profile</p>
                    </a>
                </li>
                <li>
                    <a href="logbook">
                        <i class="tim-icons icon-book-bookmark"></i>
                        <p>Flight Logbook</p>
                    </a>
                </li>
            </ul>
        </div>
    </div>
    <div class="main-panel">
        <!-- Navbar -->
        <nav class="navbar navbar-expand-lg navbar-absolute navbar-transparent">
            <div class="container-fluid">
                <div class="navbar-wrapper">
                    <div class="navbar-toggle d-inline">
                        <button type="button" class="navbar-toggler">
                            <span class="navbar-toggler-bar bar1"></span>
                            <span class="navbar-toggler-bar bar2"></span>
                            <span class="navbar-toggler-bar bar3"></span>
                        </button>
                    </div>
                    <a class="navbar-brand" href="javascript:void(0)">Pilot Dashboard</a>
                </div>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navigation" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-bar navbar-kebab"></span>
                    <span class="navbar-toggler-bar navbar-kebab"></span>
                    <span class="navbar-toggler-bar navbar-kebab"></span>
                </button>
                <div class="collapse navbar-collapse" id="navigation">
                    <ul class="navbar-nav ml-auto">
                        <li class="search-bar input-group">
                        </li>
                        <li class="dropdown nav-item">
                            <a href="#" class="dropdown-toggle nav-link" data-toggle="dropdown">
                                <div class="photo">
                                    <img src="${pageContext.request.contextPath}/static/assets/images/goco.png" alt="Profile Photo">
                                </div>
                                <b class="caret d-none d-lg-block d-xl-block"></b>
                                <p class="d-lg-none">
                                    Log out
                                </p>
                            </a>
                            <ul class="dropdown-menu dropdown-navbar">
                                <li class="nav-link"><a href="logout" class="nav-item dropdown-item">Log out</a></li>
                            </ul>
                        </li>
                        <li class="separator d-lg-none"></li>
                    </ul>
                </div>
            </div>
        </nav>
        <div class="modal modal-search fade" id="searchModal" tabindex="-1" role="dialog" aria-labelledby="searchModal" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <input type="text" class="form-control" id="inlineFormInputGroup" placeholder="SEARCH">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <i class="tim-icons icon-simple-remove"></i>
                        </button>
                    </div>
                </div>
            </div>
        </div>
        <!-- End Navbar -->
        <div class="content">
            <div class="row">
                <div class="col-12">
                    <div class="card">
                    <div class="card-header">
                        <h2 class="card-header" style="text-align: center;" >Weekly Flights</h2>
                        <div class="card-body">
                            <canvas id="weeklyChart" class="charts" style="" width="20" height="20"></canvas>
                        </div>
                    </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-12">
                    <div class="card">
                        <div class="card-header">
                            <h2 class="card-header" style="text-align: center;" >Monthly Flights</h2>
                            <div class="card-body">
                                <canvas id="monthlyChart" class="charts" style="" width="20" height="20"></canvas>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-12">
                    <div class="card">
                        <div class="card-header">
                            <h2 class="card-header" style="text-align: center;" >Yearly Flights</h2>
                            <div class="card-body">
                                <canvas id="yearlyChart" class="charts" style="" width="20" height="20"></canvas>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
                <div class="col-lg-6 col-md-12">
                    <div class="card ">
                        <div class="card-header">
                            <h4 class="card-title">Flight Log</h4>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <c:if test="${not empty user_account.flights}">
                                <table class="table tablesorter " id="">
                                    <thead class=" text-primary">
                                    <tr>
                                        <th>
                                            DAY
                                        </th>
                                        <th>
                                            NVG
                                        </th>
                                        <th>
                                            NIGHT
                                        </th>
                                        <th>
                                            HD
                                        </th>
                                        <th class="text-center">
                                            WX
                                        </th>
                                        <th class="text-center">
                                            Date
                                        </th>

                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:set var="count" value="0"/>
                                    <c:forEach var="flight" items="${flights}">
                                    <c:set var="count" value="${count+1}"/>
                                    <tr>
                                    <tr>
                                        <td>
                                                ${flight.day}
                                        </td>

                                        <td>
                                                ${flight.nvg}
                                        </td>
                                        <td>
                                                ${flight.night}
                                        </td>
                                        <td>
                                                ${flight.weather}
                                        </td>
                                        <td>
                                                ${flight.hood}
                                        </td>
                                        <td class="text-center">
                                                ${flight.dateofflight}
                                        </td>
                                    </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                                </c:if>

                            </div>

                        </div>

                    </div>

                </div>

            </div>
        </div>
        <footer class="footer">
            <div class="container-fluid">
                <div class="copyright">
                    &copy 2021 Devilray LZ
                </div>
            </div>
        </footer>
    </div>
</div>

<!--   Core JS Files   -->
<script src="${pageContext.request.contextPath}/static/assets/js/core/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/static/assets/js/core/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/static/assets/js/core/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/static/assets/js/plugins/perfect-scrollbar.jquery.min.js"></script>
<!--  Google Maps Plugin    -->
<!-- Place this tag in your head or just before your close body tag. -->
<script src="https://maps.googleapis.com/maps/api/js?key=YOUR_KEY_HERE"></script>
<!-- Chart JS -->
<script src="${pageContext.request.contextPath}/static/assets/js/plugins/chartjs.min.js"></script>
<!--  Notifications Plugin    -->
<script src="${pageContext.request.contextPath}/static/assets/js/plugins/bootstrap-notify.js"></script>
<!-- Control Center for Black Dashboard: parallax effects, scripts for the example pages etc -->
<script src="${pageContext.request.contextPath}/static/assets/js/black-dashboard.min.js?v=1.0.0"></script><!-- Black Dashboard DEMO methods, don't include it in your project! -->
<script src="${pageContext.request.contextPath}/static/assets/demo/demo.js"></script>
<script>
    $(document).ready(function() {
        $().ready(function() {
            $sidebar = $('.sidebar');
            $navbar = $('.navbar');
            $main_panel = $('.main-panel');

            $full_page = $('.full-page');

            $sidebar_responsive = $('body > .navbar-collapse');
            sidebar_mini_active = true;
            white_color = false;

            window_width = $(window).width();

            fixed_plugin_open = $('.sidebar .sidebar-wrapper .nav li.active a p').html();



            $('.fixed-plugin a').click(function(event) {
                if ($(this).hasClass('switch-trigger')) {
                    if (event.stopPropagation) {
                        event.stopPropagation();
                    } else if (window.event) {
                        window.event.cancelBubble = true;
                    }
                }
            });

            $('.fixed-plugin .background-color span').click(function() {
                $(this).siblings().removeClass('active');
                $(this).addClass('active');

                var new_color = $(this).data('color');

                if ($sidebar.length != 0) {
                    $sidebar.attr('data', new_color);
                }

                if ($main_panel.length != 0) {
                    $main_panel.attr('data', new_color);
                }

                if ($full_page.length != 0) {
                    $full_page.attr('filter-color', new_color);
                }

                if ($sidebar_responsive.length != 0) {
                    $sidebar_responsive.attr('data', new_color);
                }
            });

            $('.switch-sidebar-mini input').on("switchChange.bootstrapSwitch", function() {
                var $btn = $(this);

                if (sidebar_mini_active == true) {
                    $('body').removeClass('sidebar-mini');
                    sidebar_mini_active = false;
                    blackDashboard.showSidebarMessage('Sidebar mini deactivated...');
                } else {
                    $('body').addClass('sidebar-mini');
                    sidebar_mini_active = true;
                    blackDashboard.showSidebarMessage('Sidebar mini activated...');
                }

                // we simulate the window Resize so the charts will get updated in realtime.
                var simulateWindowResize = setInterval(function() {
                    window.dispatchEvent(new Event('resize'));
                }, 180);

                // we stop the simulation of Window Resize after the animations are completed
                setTimeout(function() {
                    clearInterval(simulateWindowResize);
                }, 1000);
            });

            $('.switch-change-color input').on("switchChange.bootstrapSwitch", function() {
                var $btn = $(this);

                if (white_color == true) {

                    $('body').addClass('change-background');
                    setTimeout(function() {
                        $('body').removeClass('change-background');
                        $('body').removeClass('white-content');
                    }, 900);
                    white_color = false;
                } else {

                    $('body').addClass('change-background');
                    setTimeout(function() {
                        $('body').removeClass('change-background');
                        $('body').addClass('white-content');
                    }, 900);

                    white_color = true;
                }


            });

            $('.light-badge').click(function() {
                $('body').addClass('white-content');
            });

            $('.dark-badge').click(function() {
                $('body').removeClass('white-content');
            });
        });
    });
</script>
<script>
    $(document).ready(function() {
        // Javascript method's body can be found in assets/js/demos.js
        demo.initDashboardPageCharts();

    });
</script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.bundle.js" integrity="sha512-zO8oeHCxetPn1Hd9PdDleg5Tw1bAaP0YmNvPY8CwcRyUk7d7/+nyElmFrB6f7vg4f7Fv4sui1mcep8RIEShczg==" crossorigin="anonymous"></script>

<script>
    var ctx = document.getElementById('weeklyChart');
    var weeklyChart = new Chart(ctx, {
        type: 'pie',
        data: {
            labels: ['DAY', 'NVG', 'NIGHT', 'WEATHER', 'HOOD'],
            datasets: [{
                label: '# of Flights',
                data: [${weeklyDay}, ${weeklyNVG}, ${weeklyNight}, ${weeklyWeather}, ${weeklyHood} ],
                backgroundColor: [
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(54, 162, 235, 0.2)',
                    'rgba(255, 206, 86, 0.2)',
                    'rgba(75, 192, 192, 0.2)',
                    'rgba(153, 102, 255, 0.2)',
                    'rgba(255, 159, 64, 0.2)'
                ],
                borderColor: [
                    'rgba(255, 99, 132, 1)',
                    'rgba(54, 162, 235, 1)',
                    'rgba(255, 206, 86, 1)',
                    'rgba(75, 192, 192, 1)',
                    'rgba(153, 102, 255, 1)',
                    'rgba(255, 159, 64, 1)'
                ],
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: true
                    }
                }]
            }
        }
    });
</script>
<script>
    var ctx = document.getElementById('monthlyChart');
    var monthlyChart = new Chart(ctx, {
        type: 'pie',
        data: {
            labels: ['DAY', 'NVG', 'NIGHT', 'WEATHER', 'HOOD'],
            datasets: [{
                label: '# of Flights',
                data: [${monthlyDay}, ${monthlyNVG}, ${monthlyNight}, ${monthlyWeather}, ${monthlyHood} ],
                backgroundColor: [
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(54, 162, 235, 0.2)',
                    'rgba(255, 206, 86, 0.2)',
                    'rgba(75, 192, 192, 0.2)',
                    'rgba(153, 102, 255, 0.2)',
                    'rgba(255, 159, 64, 0.2)'
                ],
                borderColor: [
                    'rgba(255, 99, 132, 1)',
                    'rgba(54, 162, 235, 1)',
                    'rgba(255, 206, 86, 1)',
                    'rgba(75, 192, 192, 1)',
                    'rgba(153, 102, 255, 1)',
                    'rgba(255, 159, 64, 1)'
                ],
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: true
                    }
                }]
            }
        }
    });
</script>
<script>
    var ctx = document.getElementById('yearlyChart');
    var yearlyChart = new Chart(ctx, {
        type: 'pie',
        data: {
            labels: ['DAY', 'NVG', 'NIGHT', 'WEATHER', 'HOOD'],
            datasets: [{
                label: '# of Flights',
                data: [${yearlyDay}, ${yearlyNVG}, ${yearlyNight}, ${yearlyWeather}, ${yearlyHood} ],
                backgroundColor: [
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(54, 162, 235, 0.2)',
                    'rgba(255, 206, 86, 0.2)',
                    'rgba(75, 192, 192, 0.2)',
                    'rgba(153, 102, 255, 0.2)',
                    'rgba(255, 159, 64, 0.2)'
                ],
                borderColor: [
                    'rgba(255, 99, 132, 1)',
                    'rgba(54, 162, 235, 1)',
                    'rgba(255, 206, 86, 1)',
                    'rgba(75, 192, 192, 1)',
                    'rgba(153, 102, 255, 1)',
                    'rgba(255, 159, 64, 1)'
                ],
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: true
                    }
                }]
            }
        }
    });
</script>

<style>
    .charts{
        height: 60%!important;
        width: 60%!important;
        display: block;
        margin-left: auto;
        margin-right: auto;
        margin-bottom: 0.5%;

    }
</style>
<script>
    let current = ${current}
    if(current === false){
        alert('You are uncurrent, please go check the currency tab')
    }
</script>

</body>

</html>