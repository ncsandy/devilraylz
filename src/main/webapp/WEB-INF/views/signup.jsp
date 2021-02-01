<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>

<head>
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Permanent+Marker&display=swap" rel="stylesheet">
    <title>Registration</title>
</head>
<body>

<div class="container py-5">

    <div class="row">
        <div class="col-md-12">
            <img src="${pageContext.request.contextPath}/static/assets/images/goco.png" class="logo">
            <div class="row">
                <div class="col-md-6 mx-auto">
                   <h2 class="text-center mb-5 regfont" style="margin-bottom: 0.5%;">Registration</h2>
                    <div class="card border-secondary">
                        <div class="card-header">
                            <h3 class="mb-0 my-2 regfont" style="text-align: center">Sign Up</h3>
                        </div>
                        <div class="card-body" style="margin-top: 0.5%">
                            <form:form action="register" modelAttribute="accounts" method="Post">

                                <label for="rank">Rank<span class="required"></span> </label>
                                <form:select path="pilotRank" id="rank" class="form-control"  required="required" >
                                    <form:option value="Select Rank">${accounts.pilotRank}</form:option>
                                    <c:forEach items="${rank}" var="item">
                                        <form:option value="${item}" > ${item}</form:option>
                                    </c:forEach>
                                </form:select>
                                <div class="form-group">
                                    <label for="inputName1">Birthday</label>
                                    <form:input  type="date" class="form-control" path="pBirthday"  />
                                    <form:errors path="pBirthday" class="text-danger"></form:errors>
                                </div>

                                <div class="form-group">
                                    <label for="inputName1">First Name</label>
                                    <input type="text" name="fName" class="form-control" id="inputName1" placeholder="First Name">
                                    <form:errors path="fName" class="text-danger"></form:errors>
                                </div>
                                <div class="form-group">
                                    <label for="inputName2">Last Name</label>
                                    <input type="text" class="form-control" name="lName" id="inputName2" placeholder="Last name">
                                    <form:errors path="lName" class="text-danger"></form:errors>
                                </div>
                                <div class="form-group">
                                    <label for="inputEmail3">Email</label>
                                    <input type="email" class="form-control" name="email" id="inputEmail3" placeholder="email@gmail.com" required="">
                                    <form:errors path="email" class="text-danger"></form:errors>
                                </div>
                                <div class="form-group">
                                    <label for="inputPassword3">Password</label>
                                    <input type="password" class="form-control" id="inputPassword3" name="password" placeholder="password" title="At least 6 characters with letters and numbers" required="">
                                    <form:errors path="password" class="text-danger"></form:errors>
                                </div>
                                <div class="form-group">
                                    <label for="inputVerify3">Verify</label>
                                    <input type="password" class="form-control" id="inputVerify3" name="password2" placeholder="password (again)" required="">
                                    <form:errors path="password2" class="text-danger"></form:errors>
                                </div>
                                <div class="form-group" style="display: flex;align-items: center;justify-content: center;">
                                    <button type="submit" class="btn btn-success btn-lg float-right btn-danger" id="center">Register</button>
                                </div>
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>
            <!--/row-->

        </div>
        <!--/col-->
    </div>
    <!--/row-->
</div>

<!--/container-->

<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>

<style>
    body {
        background-color: #60a3bc !important;
        background-image: url("${pageContext.request.contextPath}/static/assets/images/sky.jpg");
        background-size: cover;
    }
    .logo{
        height: 200px;
        width: auto;
        display: block;
        margin-left: auto;
        margin-right: auto;
        margin-bottom: 0.5%;
    }
    .regfont{
        font-family: 'Permanent Marker', cursive;
    }
    label{
        font-family: 'Knewave', cursive;

    }

</style>
</body>
</html>