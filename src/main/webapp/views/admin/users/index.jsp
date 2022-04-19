<%--
  Created by IntelliJ IDEA.
  User: thanhkali
  Date: 4/7/22
  Time: 5:57 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>
        .table-responsive {
            margin: 30px 0;
        }
        .table-wrapper {
            background: #fff;
            padding: 20px 25px;
            border-radius: 3px;
            min-width: 1000px;
            box-shadow: 0 1px 1px rgba(0,0,0,.05);
        }
        .table-title {
            padding-bottom: 15px;
            background: #435d7d;
            color: #fff;
            padding: 16px 30px;
            min-width: 100%;
            margin: -20px -25px 10px;
            border-radius: 3px 3px 0 0;
        }
        .table-title h2 {
            margin: 5px 0 0;
            font-size: 24px;
        }
        .table-title .btn-group {
            float: right;
        }
        .table-title .btn {
            color: #fff;
            float: right;
            font-size: 13px;
            border: none;
            min-width: 50px;
            border-radius: 2px;
            border: none;
            outline: none !important;
            margin-left: 10px;
        }
        .table-title .btn i {
            float: left;
            font-size: 21px;
            margin-right: 5px;
        }
        .table-title .btn span {
            float: left;
            margin-top: 2px;
        }
        table.table tr th, table.table tr td {
            border-color: #e9e9e9;
            padding: 12px 15px;
            vertical-align: middle;
        }
        table.table tr th:first-child {
            width: 60px;
        }
        table.table tr th:last-child {
            width: 100px;
        }
        table.table-striped tbody tr:nth-of-type(odd) {
            background-color: #fcfcfc;
        }
        table.table-striped.table-hover tbody tr:hover {
            background: #f5f5f5;
        }
        table.table th i {
            font-size: 13px;
            margin: 0 5px;
            cursor: pointer;
        }
        table.table td:last-child i {
            opacity: 0.9;
            font-size: 22px;
            margin: 0 5px;
        }
        table.table td a {
            font-weight: bold;
            color: #566787;
            display: inline-block;
            text-decoration: none;
            outline: none !important;
        }
        table.table td a:hover {
            color: #2196F3;
        }
        table.table td a.edit {
            color: #FFC107;
        }
        table.table td a.delete {
            color: #F44336;
        }
        table.table td i {
            font-size: 19px;
        }
        table.table .avatar {
            border-radius: 50%;
            vertical-align: middle;
            margin-right: 10px;
        }
        .pagination {
            float: right;
            margin: 0 0 5px;
        }
        .pagination li a {
            border: none;
            font-size: 13px;
            min-width: 30px;
            min-height: 30px;
            color: #999;
            margin: 0 2px;
            line-height: 30px;
            border-radius: 2px !important;
            text-align: center;
            padding: 0 6px;
        }
        .pagination li a:hover {
            color: #666;
        }
        .pagination li.active a, .pagination li.active a.page-link {
            background: #03A9F4;
        }
        .pagination li.active a:hover {
            background: #0397d6;
        }
        .pagination li.disabled i {
            color: #ccc;
        }
        .pagination li i {
            font-size: 16px;
            padding-top: 6px
        }
        .hint-text {
            float: left;
            margin-top: 10px;
            font-size: 13px;
        }
        /* Custom checkbox */
        .custom-checkbox {
            position: relative;
        }
        .custom-checkbox input[type="checkbox"] {
            opacity: 0;
            position: absolute;
            margin: 5px 0 0 3px;
            z-index: 9;
        }
        .custom-checkbox label:before{
            width: 18px;
            height: 18px;
        }
        .custom-checkbox label:before {
            content: '';
            margin-right: 10px;
            display: inline-block;
            vertical-align: text-top;
            background: white;
            border: 1px solid #bbb;
            border-radius: 2px;
            box-sizing: border-box;
            z-index: 2;
        }
        .custom-checkbox input[type="checkbox"]:checked + label:after {
            content: '';
            position: absolute;
            left: 6px;
            top: 3px;
            width: 6px;
            height: 11px;
            border: solid #000;
            border-width: 0 3px 3px 0;
            transform: inherit;
            z-index: 3;
            transform: rotateZ(45deg);
        }
        .custom-checkbox input[type="checkbox"]:checked + label:before {
            border-color: #03A9F4;
            background: #03A9F4;
        }
        .custom-checkbox input[type="checkbox"]:checked + label:after {
            border-color: #fff;
        }
        .custom-checkbox input[type="checkbox"]:disabled + label:before {
            color: #b8b8b8;
            cursor: auto;
            box-shadow: none;
            background: #ddd;
        }
        /* Modal styles */
        .modal .modal-dialog {
            max-width: 400px;
        }
        .modal .modal-header, .modal .modal-body, .modal .modal-footer {
            padding: 20px 30px;
        }
        .modal .modal-content {
            border-radius: 3px;
            font-size: 14px;
        }
        .modal .modal-footer {
            background: #ecf0f1;
            border-radius: 0 0 3px 3px;
        }
        .modal .modal-title {
            display: inline-block;
        }
        .modal .form-control {
            border-radius: 2px;
            box-shadow: none;
            border-color: #dddddd;
        }
        .modal textarea.form-control {
            resize: vertical;
        }
        .modal .btn {
            border-radius: 2px;
            min-width: 100px;
        }
        .modal form label {
            font-weight: normal;
        }
        .modal-confirm {
            color: #636363;
            width: 400px;
        }
        .modal-confirm .modal-content {
            padding: 20px;
            border-radius: 5px;
            border: none;
            text-align: center;
            font-size: 14px;
        }
        .modal-confirm .modal-header {
            border-bottom: none;
            position: relative;
        }
        .modal-confirm h4 {
            text-align: center;
            font-size: 26px;
            margin: 30px 0 -10px;
        }
        .modal-confirm .close {
            position: absolute;
            top: -5px;
            right: -2px;
        }
        .modal-confirm .modal-body {
            color: #999;
        }
        table.table .avatar {
            border-radius: 50%;
            vertical-align: middle;
            margin-right: 10px;
        }
        .modal-confirm .modal-footer {
            border: none;
            text-align: center;
            border-radius: 5px;
            font-size: 13px;
            padding: 10px 15px 25px;
        }
        .modal-confirm .modal-footer a {
            color: #999;
        }
        .modal-confirm .icon-box {
            width: 80px;
            height: 80px;
            margin: 0 auto;
            border-radius: 50%;
            z-index: 9;
            text-align: center;
            border: 3px solid #f15e5e;
        }
        .modal-confirm .icon-box i {
            color: #f15e5e;
            font-size: 46px;
            display: inline-block;
            margin-top: 13px;
        }
        .modal-confirm .btn, .modal-confirm .btn:active {
            color: #fff;
            border-radius: 4px;
            background: #60c7c1;
            text-decoration: none;
            transition: all 0.4s;
            line-height: normal;
            min-width: 120px;
            border: none;
            min-height: 40px;
            border-radius: 3px;
            margin: 0 5px;
        }
        .modal-confirm .btn-secondary {
            background: #c1c1c1;
        }
        .modal-confirm .btn-secondary:hover, .modal-confirm .btn-secondary:focus {
            background: #a8a8a8;
        }
        .modal-confirm .btn-danger {
            background: #f15e5e;
        }
        .modal-confirm .btn-danger:hover, .modal-confirm .btn-danger:focus {
            background: #ee3535;
        }
        .trigger-btn {
            display: inline-block;
            margin: 100px auto;
        }
    </style>
    <script>
        $(document).ready(function(){
            // Activate tooltip
            $('[data-toggle="tooltip"]').tooltip();

            // Select/Deselect checkboxes
            var checkbox = $('table tbody input[type="checkbox"]');
            $("#selectAll").click(function(){
                if(this.checked){
                    checkbox.each(function(){
                        this.checked = true;
                    });
                } else{
                    checkbox.each(function(){
                        this.checked = false;
                    });
                }
            });
            checkbox.click(function(){
                if(!this.checked){
                    $("#selectAll").prop("checked", false);
                }
            });
        });
    </script>
</head>
<body>
<c:if test="${uid==-1}">
    <c:set var = "url" scope = "session" value = "/users/store"/>
</c:if>
<c:if test="${uid!=-1}">
    <c:set var = "url" scope = "session" value = "/users/update?id=${uid}"/>
</c:if>
<div class="container">
    <h2 class="text-center text-danger mb-4">${uid==-1?"Create User":"Edit User"}</h2>
    <hr style="margin-top: 20px;margin-bottom: 50px">
    <c:if test="${!empty sessionScope.message}">
        <div class="alert alert-success">
                ${sessionScope.message}
            <c:remove var="message" scope="session"></c:remove>
        </div>
    </c:if>
    <c:if test="${!empty sessionScope.error}">
        <div class="alert alert-danger">
                ${sessionScope.error}
            <c:remove var="error" scope="session"></c:remove>
        </div>
    </c:if>
    <form method="POST"
          action="<c:out value = "${url}"/>" enctype="multipart/form-data">
        <div class="mb-3">
            <label class="form-label">Email address</label>
            <input type="email" class="form-control" placeholder="name@gmail.com" name="email" value="${user.email}">
        </div>
        <c:if test="${uid==-1}">
            <div class="mb-3">
                <label class="form-label">Password User</label>
                <input type="password" class="form-control" placeholder="Password User" name="passwordUser">
            </div>
            <div class="mb-3">
                <label class="form-label">Confirm Password</label>
                <input type="password" class="form-control" placeholder="Confirm Password" name="confirmPassword">
            </div>
        </c:if>
        <div class="mb-3">
            <label class="form-label">Full Name</label>
            <input type="text" class="form-control" placeholder="Full Name" name="name" value="${user.name}">
        </div>
        <div class="mb-3">
            <label class="form-label">Address</label>
            <input type="text" class="form-control" placeholder="Address" name="address" value="${user.address}">
        <div class="mb-3">
            <label class="form-label">Birthday</label>
            <input type="date" class="form-control" placeholder="Birthday" name="birthday" value="${user.birthday}">
        </div>
        </div>
        <div class="mb-3">
            <label class="form-label">Phone</label>
            <input type="text" class="form-control" placeholder="Number Phone" name="numberPhone" value="${user.numberPhone}">
        </div>
        <c:if test="${uid==-1}">
            <div class="mb-3">
                <label class="form-label">Sex</label>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="sex" id="inlineRadio1" value="1" checked>
                    <label class="form-check-label" for="inlineRadio1">Nam</label>
                    <input class="form-check-input" type="radio" name="sex" id="inlineRadio2" value="0">
                    <label class="form-check-label" for="inlineRadio2">Nữ</label>
                </div>
            </div>
        </c:if>
        <c:if test="${uid!=-1}">
            <div class="mb-3">
                <label class="form-label">Sex</label>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="sex" id="inlineRadio1" value="1" ${ user.sex == 1 ? "checked" : "" }>
                    <label class="form-check-label" for="inlineRadio1">Nam</label>
                    <input class="form-check-input" type="radio" name="sex" id="inlineRadio2" value="0" ${ user.sex == 0 ? "checked" : "" }>
                    <label class="form-check-label" for="inlineRadio2">Nữ</label>
                </div>
            </div>
        </c:if>
        <c:if test="${uid==-1}">
            <div class="mb-3">
                <label class="form-label">Sex</label>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="isAdmin" id="rd1" value="1" checked>
                    <label class="form-check-label" for="inlineRadio1">Admin</label>
                    <input class="form-check-input" type="radio" name="isAdmin" id="rd2" value="0">
                    <label class="form-check-label" for="inlineRadio2">User</label>
                </div>
            </div>
        </c:if>
        <c:if test="${uid!=-1}">
            <div class="mb-3">
                <label class="form-label">Sex</label>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="isAdmin" id="rd1" value="1" ${ user.isAdmin == 1 ? "checked" : "" }>
                    <label class="form-check-label" for="inlineRadio1">Admin</label>
                    <input class="form-check-input" type="radio" name="isAdmin" id="rd2" value="0" ${ user.isAdmin == 0 ? "checked" : "" }>
                    <label class="form-check-label" for="inlineRadio2">User</label>
                </div>
            </div>
        </c:if>

        <div class="mb-3">
            <label for="formFileSm" class="form-label">Hình ảnh</label>
            <input class="form-control form-control-sm" id="formFileSm" name="avatar" type="file">
        </div>
        <div style="margin-top: 20px;padding-left: 5px">
            <button class="btn btn-info ${uid!=-1 ? 'disabled' :''}">Create</button>
            <button class="btn btn-info ${uid==-1 ? 'disabled' :''}">Save</button>
            <button href="#deleteEmployeeModal" data-toggle="modal" class="btn btn-danger  ${uid==-1 ? 'disabled' :''}">Delete</button>
            <button class="btn btn-info ${uid!=-1 ? 'disabled' :''}" type="reset">Xóa form</button>
        </div>
    </form>
</div>
<div class="container-fluid">
    <div class="table-responsive">
        <div class="table-wrapper">
            <div class="table-title">
                <div class="row">
                    <div class="col-sm-6">
                        <h2>Manage <b>Users</b></h2>
                    </div>
                </div>
            </div>
            <table class="table table-striped table-hover">
                <thead>
                <tr>
                    <th scope="col">Email</th>
                    <th scope="col">Name</th>
                    <th scope="col">Address</th>
                    <th scope="col">Phone</th>
                    <th scope="col">Sex</th>
                    <th scope="col">Birthday</th>
                    <th scope="colgroup">Avatar</th>
                    <th colspan="2" scope="col">Option</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="user" items="${ds}">
                    <tr>
                        <td>${user.email}</td>
                        <td>${user.name}</td>
                        <td>${user.address}</td>
                        <td>${user.numberPhone}</td>
                        <td>${user.sex == 1 ? "nam": "nữ"}</td>
                        <td>${user.birthday}</td>
                        <td><a href="#"><img  style="height: 50px" src="../images/avatar/${user.avatar}" class="avatar" alt="Avatar"></a></td>
                        <td>
                            <form action="edit" method="post">
                                <button class="edit btn" value="${user.id}" name="uid"><i class="material-icons">&#xE254;</i></button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div class="clearfix">
                <div class="hint-text">Showing <b>5</b> out of <b>25</b> entries</div>
                <ul class="pagination">
                    <li class="page-item disabled"><a href="#">Previous</a></li>
                    <li class="page-item"><a href="#" class="page-link">1</a></li>
                    <li class="page-item"><a href="#" class="page-link">2</a></li>
                    <li class="page-item active"><a href="#" class="page-link">3</a></li>
                    <li class="page-item"><a href="#" class="page-link">4</a></li>
                    <li class="page-item"><a href="#" class="page-link">5</a></li>
                    <li class="page-item"><a href="#" class="page-link">Next</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>
<!-- Edit Modal HTML -->
<div id="addEmployeeModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <form>
                <div class="modal-header">
                    <h4 class="modal-title">Add User</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label>Name</label>
                        <input type="text" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Email</label>
                        <input type="email" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Address</label>
                        <textarea class="form-control" required></textarea>
                    </div>
                    <div class="form-group">
                        <label>Phone</label>
                        <input type="text" class="form-control" required>
                    </div>
                </div>
                <div class="modal-footer">
                    <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                    <input type="submit" class="btn btn-success" value="Add">
                </div>
            </form>
        </div>
    </div>
</div>
<!-- Edit Modal HTML -->
<div id="editEmployeeModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <form>
                <div class="modal-header">
                    <h4 class="modal-title">Edit Employee</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label>Name</label>
                        <input type="text" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Email</label>
                        <input type="email" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Address</label>
                        <textarea class="form-control" required></textarea>
                    </div>
                    <div class="form-group">
                        <label>Phone</label>
                        <input type="text" class="form-control" required>
                    </div>
                </div>
                <div class="modal-footer">
                    <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                    <input type="submit" class="btn btn-info" value="Save">
                </div>
            </form>
        </div>
    </div>
</div>
<!-- Delete Modal HTML -->
<!-- Modal HTML -->
<div id="deleteEmployeeModal" class="modal fade">
    <div class="modal-dialog modal-confirm">
        <div class="modal-content">
            <div class="modal-header flex-column">
                <div class="icon-box">
                    <i class="material-icons">&#xE5CD;</i>
                </div>
                <h4 class="modal-title w-100">Are you sure?</h4>
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            </div>
            <div class="modal-body">
                <c:forEach var="user" items="${ds}">
                    <c:if test="${user.id == uid}">
                        <p>Are you sure you want to delete your account ${user.name}?This process cannot be undone.</p>
                    </c:if>
                </c:forEach>
            </div>
            <div class="justify-content-center">
                <form action="delete?uid=${uid}" method="post">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                    <button type="submit" class="btn btn-danger">Delete</button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
