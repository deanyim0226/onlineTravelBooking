<%@page language="java" contentType="text/html; ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <script src="../js/jquery-3.7.1.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <title>Review</title>
    <style>
        .checked {
            color: orange;
        }
    </style>
    <script>
        $(document).ready(function(){

            let hotelId = $("#modal_hotelId").val();
            let hotelName = $("#modal_hotelName").val();
            let reviews = []
            $.ajax({
                type:"GET",
                url:"/getReviews/" + hotelId,
                async: false,
                success:function(response){

                    $.each(response, function(index,review){
                        reviews.push(review);
                    })

                },
                error:function(err){
                    alert("error while retrieving reviews "  +err)
                }
            })


            $("#review-table tr:not(:first)").remove();
            let numberOfReviews = reviews.length;
            let averageService = 0;
            let averageAmenities = 0;
            let averageProcess = 0;
            let averageExperience = 0;

            let averageOverall = 0;
            let map = new Map();
            for(let i =1; i<6; i++){
                map.set(i,0)
            }

            $.each(reviews, function(index, review){

                if(review.serviceRating < 1.5){
                    let frequency = map.get(1)
                    map.set(1, frequency + 1);
                }
                if(review.amenitiesRating < 1.5){
                    let frequency = map.get(1)
                    map.set(1, frequency + 1);
                }
                if(review.bookingProcessRating < 1.5){
                    let frequency = map.get(1)
                    map.set(1, frequency + 1);
                }
                if(review.wholeExpRating < 1.5){
                    let frequency = map.get(1)
                    map.set(1, frequency + 1);
                }
                if(review.overallRating < 1.5 ) {
                    let frequency = map.get(1)
                    map.set(1, frequency + 1);
                }


                    if( review.serviceRating > 1.5 && review.serviceRating < 2.5){
                        let frequency = map.get(2)
                        map.set(2,frequency+1);
                    }
                    if(review.amenitiesRating > 1.5 && review.amenitiesRating < 2.5){
                        let frequency = map.get(2)
                        map.set(2,frequency+1);
                    }
                    if(review.bookingProcessRating > 1.5 &&  review.bookingProcessRating < 2.5){
                        let frequency = map.get(2)
                        map.set(2,frequency+1);
                    }
                    if(review.wholeExpRating > 1.5 &&  review.wholeExpRating < 2.5){
                        let frequency = map.get(2)
                        map.set(2,frequency+1);
                    }
                    if(review.overallRating > 1.5 &&  review.overallRating < 2.5) {
                        let frequency = map.get(2)
                        map.set(2,frequency+1);
                    }

                    if( review.serviceRating > 2.5 && review.serviceRating < 3.5) {
                        let frequency = map.get(3)
                        map.set(3,frequency+1);
                    }
                    if(review.amenitiesRating > 2.5 && review.amenitiesRating < 3.5) {
                        let frequency = map.get(3)
                        map.set(3,frequency+1);
                    }
                    if(review.bookingProcessRating > 2.5 && review.bookingProcessRating < 3.5){
                        let frequency = map.get(3)
                        map.set(3,frequency+1);
                    }
                    if(review.wholeExpRating > 2.5 && review.wholeExpRating < 3.5) {
                        let frequency = map.get(3)
                        map.set(3,frequency+1);
                    }
                    if(review.overallRating > 2.5 && review.overallRating < 3.5) {
                        let frequency = map.get(3)
                        map.set(3,frequency+1);
                    }

                    if(review.serviceRating > 3.5 && review.serviceRating < 4.5){
                        let frequency = map.get(4)
                        map.set(4,frequency+1);
                    }
                    if (review.amenitiesRating > 3.5 && review.amenitiesRating < 4.5){
                        let frequency = map.get(4)
                        map.set(4,frequency+1);
                    }
                    if( review.bookingProcessRating > 3.5 && review.bookingProcessRating < 4.5){
                        let frequency = map.get(4)
                        map.set(4,frequency+1);
                    }
                    if(review.wholeExpRating > 3.5 && review.wholeExpRating < 4.5){
                        let frequency = map.get(4)
                        map.set(4,frequency+1);
                    }
                    if(review.overallRating > 3.5 && review.overallRating < 4.5){
                        let frequency = map.get(4)
                        map.set(4,frequency+1);
                    }


                    if(review.serviceRating > 4.5)
                    {
                        let frequency = map.get(5)
                        map.set(5,frequency+1);
                    }
                    if(review.amenitiesRating > 4.5){
                        let frequency = map.get(5)
                        map.set(5,frequency+1);
                    }
                    if(review.bookingProcessRating > 4.5){
                        let frequency = map.get(5)
                        map.set(5,frequency+1);
                    }
                    if(review.wholeExpRating > 4.5){
                        let frequency = map.get(5)
                        map.set(5,frequency+1);
                    }
                    if(review.overallRating > 4.5){
                        let frequency = map.get(5)
                        map.set(5,frequency+1);
                    }

                averageService += review.serviceRating;
                averageAmenities += review.amenitiesRating;
                averageProcess += review.bookingProcessRating;
                averageExperience += review.wholeExpRating;
                averageOverall += review.overallRating;

                $("#review-table").append("<tr><td>" + review.booking.userName  + "</td><td>" + review.text  + "</td></tr>")

            })
            averageService /= numberOfReviews
            averageAmenities /= numberOfReviews
            averageProcess /= numberOfReviews
            averageExperience /= numberOfReviews
            averageOverall /= numberOfReviews

            totalAverage = averageOverall + averageAmenities + averageProcess + averageService + averageExperience
            totalAverage /= 5;

            let convertedToPercentage = (totalAverage / 5) * 100;

            $("#average").text(totalAverage.toFixed(2) + " (" + numberOfReviews + " Reviews)")

            let count = 1;
            $(".star").each(function(){

                if(count <= totalAverage.toFixed(2)){
                    $(this).css("color","orange");
                }
                count += 1;
            })

            $("#percentage").text(convertedToPercentage.toFixed(2) + "% recommend this hotel")

            $("#overall").text(averageOverall.toFixed(2))
            $("#service").text(averageService.toFixed(2))
            $("#process").text(averageProcess.toFixed(2))
            $("#experience").text(averageExperience.toFixed(2))
            $("#amenities").text(averageAmenities.toFixed(2))

            let oneStar = map.get(1)
            let twoStar = map.get(2)
            let threeStar = map.get(3)
            let fourStar = map.get(4)
            let fiveStar = map.get(5)

            let total = parseInt(oneStar)+ parseInt(twoStar) + parseInt(twoStar) + parseInt(threeStar) + parseInt(fourStar) + parseInt(fiveStar)

            oneStar = (oneStar/total) * 100
            twoStar = (twoStar/total) * 100
            threeStar = (threeStar/total) *100
            fourStar = (fourStar/total) *100
            fiveStar = (fiveStar/total) *100

            $("#1star-p").val(oneStar)
            $("#2star-p").val(twoStar)
            $("#3star-p").val(threeStar)
            $("#4star-p").val(fourStar)
            $("#5star-p").val(fiveStar)

            $("#1star").text(oneStar.toFixed(2) + "%")
            $("#2star").text(twoStar.toFixed(2) + "%")
            $("#3star").text(threeStar.toFixed(2) + "%")
            $("#4star").text(fourStar.toFixed(2) + "%")
            $("#5star").text(fiveStar.toFixed(2) + "%")
        })
    </script>
</head>
<body>
<header>
    <nav class="navbar bg-dark border-bottom border-body" data-bs-theme="dark">
        <a class="btn btn-dark dropdown"   href="home"  >HOME</a>
        <ul class="nav justify-content-end">
            <s:authorize access="isAuthenticated()">

                <s:authorize access="hasAuthority('Admin')">
                    <li class = "nav-item"><a class="btn btn-dark dropdown"  href="user" >USER</a></li>
                    <li class = "nav-item"><a class="btn btn-dark dropdown"  href="role" >ROLE</a></li>
                </s:authorize>
                <li class = "nav-item"><a class="btn btn-dark dropdown"   href="reservation">RESERVATION </a></li>

                <li class = "nav-item"><a class="btn btn-dark dropdown"  href="/logout">LOGOUT</a></li>
            </s:authorize>

            <c:if test="${isLoggin}">
                <li class = "nav-item"><a class="btn btn-dark dropdown"  href="/login">LOGIN</a></li>
            </c:if>

        </ul>
    </nav>
</header>

    <div align="center" >
        <input class="form-control" type="hidden" id="modal_hotelId" value="${hotelId}"/>
        <input class="form-control" type="hidden" id="modal_hotelName" value="${hotelName}"/>
        <h1>Hotel ${hotelName}</h1>

        <div>
            <div class="row">
                <div class="col">
                    <h2>Rating & Reviews</h2>
                    <h4 id="percentage"></h4>
                    <span class="star fa fa-star"></span>
                    <span class="star fa fa-star"></span>
                    <span class="star fa fa-star"></span>
                    <span class="star fa fa-star"></span>
                    <span class="star fa fa-star"></span>
                    <h5 id="average"></h5>

                </div>
                <div class="col">
                    <ul style="list-style-type: none;">
                        <li>
                            <div class="row">
                                <div class="col"><b>Service</b>
                                <p id="service"></p></div>
                                <div class="col"><b>Amenities</b>
                                    <p id="amenities"></p>
                                </div>
                                <div class="col"><b>Process</b>
                                    <p id="process"></p>
                                </div>
                                <div class="col"><b>Experience</b>
                                    <p id="experience"></p>
                                </div>
                                <div class="col"><b>Overall</b>
                                    <p id="overall"></p>
                                </div>
                            </div>
                         </li>
                        <li>
                            <div class="row">
                                <div class="col-3">5 star</div>
                                <div class="col-6"><progress id="5star-p"  max="100"> </progress></div>
                                <div class="col-3" id="5star">
                                </div>
                            </div>
                           </li>
                        <li>
                            <div class="row">
                            <div class="col-3">4 star</div>
                            <div class="col-6"><progress id="4star-p"  max="100"/></div>
                            <div class="col-3" id="4star"></div>
                        </div></li>
                        <li>
                            <div class="row">
                            <div class="col-3">3 star</div>
                            <div class="col-6"><progress id="3star-p"  max="100"/> </div>
                            <div class="col-3" id="3star"></div>
                        </div></li>
                        <li>
                            <div class="row">
                                <div class="col-3">2 star</div>
                                <div class="col-6"><progress id="2star-p" max="100"/> </div>
                                <div class="col-3" id="2star"></div>
                            </div>
                        </li>
                        <li>
                            <div class="row">
                                <div class="col-3">1 star</div>
                                <div class="col-6"><progress id="1star-p" max="100"/></div>
                                <div class="col-3" id="1star"></div>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>

        </div>

        <div>
        <table class="table table-light table-striped-column" id="review-table">
            <tr>
                <th>Customer</th>
                <th>Comments</th>
            </tr>

        </table>
        </div>
    </div>




</body>
</html>