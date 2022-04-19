<%--
  Created by IntelliJ IDEA.
  User: thanhkali
  Date: 4/11/22
  Time: 9:12 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<style>
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

  .custom-checkbox label:before {
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
<section id="cart_items">
  <div class="container">
    <div class="breadcrumbs">
      <ol class="breadcrumb">
        <li><a href="#">Home</a></li>
        <li class="active">Shopping Cart</li>
      </ol>
    </div>
    <div class="table-responsive cart_info">
      <table class="table table-condensed">
        <thead>
        <tr class="cart_menu">
          <td class="image">Item</td>
          <td class="description"></td>
          <td class="Size">Size</td>
          <td class="Color">Color</td>
          <td class="price">Price</td>
          <td class="quantity">Quantity</td>
          <td class="total">Total</td>
          <td></td>
        </tr>
        </thead>
        <tbody>

        <c:forEach items="${list}" var="o">
          <tr>
            <td class="cart_product">
              <a href=""><img src="../images/img/${o.idProduct.img}" alt="hinh"></a>
            </td>
            <td class="cart_description">
              <h4><a href="">${o.idProduct.nameProduct}</a></h4>
              <p>Web ID: ${o.id}</p>
            </td>
            <td><p>${o.idSize.nameSize}</p></td>
            <td><p>${o.idColor.nameColor}</p></td>
            <td class="cart_price">
              <p><fmt:formatNumber value="${o.price}"
                                   pattern="#,###,000"></fmt:formatNumber>VND</p>
            </td>
            <td class="cart_quantity">
              <div class="cart_quantity_button">
                <a class="cart_quantity_up" href="cart?id=${o.id}"> + </a>
                  <input class="cart_quantity_input" type="text" name="quantity" value="${o.amount}" autocomplete="off" size="2">
                <a class="cart_quantity_down" href="sub?id=${o.id}"> - </a>
              </div>
            </td>
            <td class="cart_total">
              <p class="cart_total_price"><fmt:formatNumber value="${o.price*o.amount}" pattern="#,###,000"></fmt:formatNumber> VND</p>
            </td>
            <td class="cart_delete">
              <button type="button" data-toggle="modal" data-target="#p${o.id}" class="cart_quantity_delete btn" name="pid"><i
                      class="fa fa-times"></i></button>
            </td>
          </tr>
        </c:forEach>

        </tbody>
      </table>
    </div>
  </div>
</section> <!--/#cart_items-->
<c:forEach var="o" items="${list}">
  <div id="p${o.id}" class="modal fade">
    <div class="modal-dialog modal-confirm">
      <div class="modal-content">
        <div class="modal-header flex-column">
          <div class="icon-box">
            <i class="material-icons"></i>
          </div>
          <h4 class="modal-title w-100">Are you sure?</h4>
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        </div>
        <div class="modal-body">
          <p>Are you sure you want to delete Product ${o.idProduct.nameProduct}?This process cannot be undone.</p>
        </div>
        <div class="justify-content-center">
          <form action="delete?id=${o.id}" method="post">
            <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
            <button type="submit" class="btn btn-danger">Delete</button>
          </form>
        </div>
      </div>
    </div>
  </div>
</c:forEach>
<section id="do_action">
  <div class="container">
    <div class="heading">
      <h3>What would you like to do next?</h3>
      <p>Fill in your information here and place your order.</p>
    </div>
    <div class="row">
      <div class="col-sm-6">
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
              action="/customer/store">
          <div class="mb-3">
            <label class="form-label">Email address</label>
            <input type="email" class="form-control" placeholder="name@gmail.com" name="email">
          </div>
          <div class="mb-3">
            <label class="form-label">Full Name</label>
            <input type="text" class="form-control" placeholder="Full Name" name="name">
          </div>
          <div class="mb-3">
            <label class="form-label">Address</label>
            <input type="text" class="form-control" placeholder="Address" name="address">
            <div class="mb-3">
              <label class="form-label">Birthday</label>
              <input type="date" class="form-control" placeholder="Birthday" name="birthday">
            </div>
          </div>
          <div class="mb-3">
            <label class="form-label">Phone</label>
            <input type="text" class="form-control" placeholder="Number Phone" name="numberPhone">
          </div>
          <div class="mb-3">
            <label class="form-label">Sex</label>
            <div class="form-check form-check-inline">
              <input class="form-check-input" type="radio" name="sex" id="inlineRadio1" value="1" checked>
              <label class="form-check-label" for="inlineRadio1">Nam</label>
              <input class="form-check-input" type="radio" name="sex" id="inlineRadio2" value="0">
              <label class="form-check-label" for="inlineRadio2">Nữ</label>
            </div>
          </div>
          <button class="btn btn btn-default update btn-info">Qrder</button>
      </form>
      </div>
      <div class="col-sm-6">
        <div class="total_area">
          <ul>
            <li>Cart Sub Total <span><fmt:formatNumber value="${total}" pattern="#,###,000"></fmt:formatNumber>VND</span></li>
            <li>Shipping Cost <span>Free</span></li>
            <li>Total <span><fmt:formatNumber value="${total}" pattern="#,###,000"></fmt:formatNumber> VND</span></li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</section><!--/#do_action-->
