<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@include file="db/db.jsp" %>
<div class="row">
    <div class="col-md-12">
        <h1>利润表</h1>
    </div>
</div>
<table class="table table-bordered">
    <tr>
        <td colspan="18">公司：<c:if test="${sheets.size()>0}">${sheets.get(0).corpName}</c:if></td>
        <td><c:if test="${sheets.size()>0}">${sheets.get(0).unit}</c:if></td>
    </tr>
    <tr>
        <td>项目</td>
        <td>行次</td>
        <td>2018-1-31</td>
        <td>2018-2-28</td>
        <td>2018-3-31</td>
        <td>2018-4-30</td>
        <td>2018-5-31</td>
        <td>2018-6-30</td>
        <td>2018-7-31</td>
        <td>2018-8-31</td>
        <td>2018-9-30</td>
        <td>2018-10-31</td>
        <td>2018-11-30</td>
        <td>2018-12-31</td>
        <td>本年累计</td>
        <td>上年同期累计</td>
        <td>环比上月增长率</td>
        <td>同比增长率</td>
        <td>变动原因解析</td>
    </tr>
    <c:forEach items="${sheets}" var="s" varStatus="status">
        <tr>
            <td>${s.subjectName}</td>
            <td>${status.count}</td>
            <td>${s.month01<=0?'':s.month01}</td>
            <td>${s.month02<=0?'':s.month02}</td>
            <td>${s.month03<=0?'':s.month03}</td>
            <td>${s.month04<=0?'':s.month04}</td>
            <td>${s.month05<=0?'':s.month05}</td>
            <td>${s.month06<=0?'':s.month06}</td>
            <td>${s.month07<=0?'':s.month07}</td>
            <td>${s.month08<=0?'':s.month08}</td>
            <td>${s.month09<=0?'':s.month09}</td>
            <td>${s.month10<=0?'':s.month10}</td>
            <td>${s.month11<=0?'':s.month11}</td>
            <td>${s.month12<=0?'':s.month12}</td>
            <td>${s.yearTotal}</td>
            <td>${s.lastyears}</td>
            <td>${(s.getval(month)-(s.getval(month-1)))/(s.getval(month-1)<=0?1:s.getval(month-1))}</td>
            <td>${s.yearTotal-s.lastyears/(s.lastyears>0?s.lastyears:1)}</td>
            <td>
                <button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal"
                        onclick="fx(${s.id},${month})">分析
                </button>
            </td>
        </tr>
    </c:forEach>
</table>