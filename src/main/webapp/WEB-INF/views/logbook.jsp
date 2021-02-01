<!--
=========================================================
* * Black Dashboard - v1.0.1
=========================================================

* Product Page: https://www.creative-tim.com/product/black-dashboard
* Copyright 2019 Creative Tim (https://www.creative-tim.com)


* Coded by Creative Tim

=========================================================

* The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
-->
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
    <link rel="apple-touch-icon" sizes="76x76" href="../assets/img/apple-icon.png">
    <link rel="icon" type="image/png" href="../assets/img/favicon.png">
    <title>
       Flight Logbook
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
                    <a href="profile">
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
                <div class="col-md-12">
                    <div class="card ">
                        <div class="card-header">
                            <h4 class="card-title" style="text-align: center"> Flight Logbook Overview</h4>
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
                                            WEATHER
                                        </th>
                                        <th>
                                            HOOD
                                        </th>
                                        <th class="text-center">
                                            DATE
                                        </th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:set var="count" value="0"/>
                                    <c:forEach var="flight" items="${flights}">
                                    <c:set var="count" value="${count+1}"/>
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
                                        <td>

                                        <a href="deleteflight?id=${flight.id}"  onclick="confirmed(); return false;" ><i class="tim-icons icon-trash-simple"></i></a>
                                        </td>
                                    </tr>
                                    </c:forEach>
                                    </tbody>

                                    <tr>

                                        <td>
                                            Total
                                        </td>
                                        <td>
                                            Total
                                        </td>
                                        <td>
                                            Total
                                        </td>
                                        <td>
                                            Total
                                        </td>
                                        <td>
                                            Total
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            ${daytotal}
                                        </td>
                                        <td>
                                                ${nvgtotal}
                                        </td>
                                        <td>
                                                ${nighttotal}
                                        </td>
                                        <td>
                                                ${weathertotal}
                                        </td>
                                        <td>
                                                ${hoodtotal}
                                        </td>
                                    </tr>
                                </table>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <form:form action="addflight" modelAttribute="addflight" method="post" role="form"  >

                <table>
                    <tr>
                        <th>
                            <label>DAY</label>
                                                        <label>
                                                            <form:input  type="text" class="form-control only-numeric" style="width: 65px;" path="day" id="numb"   />
                                                        </label>
                        </th>
                        <th>
                            <label>NVG</label>
                                                        <label>
                                                            <form:input  type="text" class="form-control only-numeric" style="width: 65px;" path="nvg" id="numb"  />
                                                        </label>
                        </th>
                        <th>
                            <label>NIGHT</label>
                                                        <label>
                                                            <form:input  type="text" class="form-control only-numeric" style="width: 65px;" path="night" id="numb" />
                                                        </label>
                        </th>
                        <th>
                            <label>WX</label>
                                                        <label>
                                                            <form:input  type="text" class="form-control only-numeric" style="width: 65px;"  path="weather" id="numb"  />
                                                        </label>
                        </th>
                        <th>
                            <label>HD</label>
                                                        <label>
                                                            <form:input  type="text" class="form-control only-numeric" style="width: 65px;"  path="hood" id="numb"  />
                                                        </label>

                        </th>
                       <th>
                        <label>
                            <label>Date</label>
                                                        <label>
                                    <form:input  type="date" class="form-control" style="width: 200px;" path="dateofflight"   />
                                                        </label>
                        </label>
                       </th>
                    </tr>
                </table>
                <p id="warning"></p>
                <button type="submit"  class="btn btn-fill btn-primary" >Save</button>
                <form:input type="hidden" path="id" value="${user_account.id}"/>
                <form:input type="hidden" path="accounts.id" value="${user_account.id}"/>
            </form:form>
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
    function confirmed(){
        if (confirm('You are about to delete, Do you want to proceed?')) {
            document.getElementById("del").submit();
            return true;
        } else {
            return false;
        }
    }
</script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
    $(function () {
        $("input[id*='numb']").keydown(function (event) {


            if (event.shiftKey == true) {
                event.preventDefault();
            }

            if ((event.keyCode >= 48 && event.keyCode <= 57) ||
                (event.keyCode >= 96 && event.keyCode <= 105) ||
                event.keyCode == 8 || event.keyCode == 9 || event.keyCode == 37 ||
                event.keyCode == 39 || event.keyCode == 46 || event.keyCode == 190) {

            } else {
                event.preventDefault();
            }

            if($(this).val().indexOf('.') !== -1 && event.keyCode == 190)
                event.preventDefault();
            //if a decimal has been added, disable the "."-button

        });
    });
</script>

</body>
</html>