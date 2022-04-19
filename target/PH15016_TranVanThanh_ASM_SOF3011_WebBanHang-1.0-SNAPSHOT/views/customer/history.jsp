<%--
  Created by IntelliJ IDEA.
  User: thanhkali
  Date: 4/12/22
  Time: 2:30 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<!-- Start Content -->
<div class="container">
    <div class="row text-center fs-1">
        <span class="fw-lighter">Cart</span>
        <h2 class="fw-normal">History</h2>
    </div>
    <div class="row">
        <table class="table">
            <thead>
            <tr>
                <th>Người nhận</th>
                <th>Địa chỉ</th>
                <th>SĐT</th>
                <th>Sản phẩm</th>
                <th>Ngày đặt</th>
                <th>Tổng tiền</th>
                <th>Trạng thái</th>
            </tr>
            </thead>
            <tbody>

            <c:forEach items="${orders}" var="entry">
                <tr>
                    <td>${entry.idCustomer.name}</td>
                    <td>${entry.idCustomer.address}</td>
                    <td>${entry.idCustomer.numberPhone}</td>
                    <td>
                        <c:set var = "total" scope = "session" value = "0"/>
                        <c:forEach items="${entry.ordersDetailList}" var="item">
                            <c:set var = "total" scope = "session" value = "${total+(item.quantily*item.price)}"/>
                            ${item.idProductDetails.idProduct.nameProduct }(Color: ${item.idProductDetails.idColor.nameColor}),(Size: ${item.idProductDetails.idSize.nameSize}),(SL: ${item.quantily})<br>
                        </c:forEach>
                    </td>
                    <td><fmt:formatDate value="${entry.dateCreate}" pattern="dd/MM/yyyy"></fmt:formatDate> </td>
                    <td><fmt:formatNumber pattern="##,### VNĐ" value="${total}"></fmt:formatNumber> </td>
                    <td>
                        <c:choose>
                            <c:when test="${ entry.status == 0 }">Xử lý</c:when>
                            <c:when test="${ entry.status == 1 }">Đã hủy</c:when>
                            <c:when test="${ entry.status == 2 }">Xác nhận</c:when>
                            <c:otherwise> - </c:otherwise>
                        </c:choose>
                    </td>
                </tr>

            </c:forEach>
            </tbody>
        </table>
    </div>
</div>


<!-- End Content -->
