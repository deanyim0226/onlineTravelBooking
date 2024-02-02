<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Home Page of Travel Gig</title>
    <script src="../js/jquery-3.7.1.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="home.css">

    <script>
        $(document).ready(function(){

            $("#upcoming").css("color","black")
            $("#completed").css("color","black")
            $("#canceled").css("color","black")

            $("#canceled-body").hide()
            $("#upcoming-body").hide()
            $("#completed-body").hide()

            $("#upcoming").click(function(){

                $(this).parent().css("background-color","orange")
                $("#completed").parent().css("background-color", "white")
                $("#canceled").parent().css("background-color", "white")

                let upcomingBookings = []

                $("#upcoming-body").show()
                $("#canceled-body").hide()
                $("#completed-body").hide()

                $("#upcoming-table tr:not(:first)").remove();

                $.ajax({
                    type:"GET",
                    url:"http://localhost:8282/getUpcomingBookings",
                    async: false,
                    success:function(response){

                        $.each(response, function(index,booking){
                            upcomingBookings.push(booking);
                        })
                    },
                    error:function(err){
                        alert("error while retrieving data upcoming bookings " + err );
                    }
                })

                $.each(upcomingBookings, function(index, booking){

                    $.ajax({
                        type: "GET",
                        url:"http://localhost:8282/searchHotelById/"+booking.hotelId,
                        async: false,
                        success:function(response){

                            $("#upcoming-table").append(
                                "<tr><td>" + response.hotelName +"</td><td>"+ booking.roomType  +"</td><td>"+ booking.userName  +"</td><td>"+ booking.checkInDate +"</td><td>"+ booking.checkOutDate
                                +"</td><td>$"+ booking.finalCharges +"</td><td>"+ booking.status +"</td><td><button class='cancel btn btn-danger' data-bookingId='"+booking.bookingId+"'>Cancel</button></td></tr>"
                            )

                        },
                        error:function(err){
                            alert("error while retrieving hotel information" + err)
                        }
                    })
                })

                $(".cancel").each(function(index,element){
                    $(element).click(function(){

                        let hotelBookingId = $(element).attr("data-bookingId")
                        //open modal to confirm the final decision

                        $.ajax({
                            type:"PUT",
                            url:"http://localhost:8282/cancelBooking/" + hotelBookingId,
                            async: false,
                            contentType: 'application/json', // Set the content type if you are sending JSON data
                            data: JSON.stringify(hotelBookingId),
                            success:function(response){
                                window.location.replace("reservation")
                                alert("successfully canceled reservation")
                            },
                            error:function(err){
                                alert("error while updating hotel information" + err.toString())
                            }
                        })
                    })
                })

            })

            $("#canceled").click(function(){
                $(this).parent().css("background-color","orange")
                $("#completed").parent().css("background-color", "white")
                $("#upcoming").parent().css("background-color", "white")

                $("#canceled-body").show()
                $("#upcoming-body").hide()
                $("#completed-body").hide()

                let canceledBookings = []

                $("#canceled-table tr:not(:first)").remove();

                $.ajax({
                    type:"GET",
                    url:"http://localhost:8282/getCanceledBookings",
                    async: false,
                    success:function(response){

                        $.each(response, function(index,booking){
                            canceledBookings.push(booking);
                        })
                    },
                    error:function(err){
                        alert("error while retrieving data canceled bookings " + err );
                    }
                })

                $.each(canceledBookings, function(index, booking){

                    $.ajax({
                        type: "GET",
                        url:"http://localhost:8282/searchHotelById/"+booking.hotelId,
                        async: false,
                        success:function(response){

                            $("#canceled-table").append(
                                "<tr><td>" + response.hotelName +"</td><td>"+ booking.roomType  +"</td><td>"+ booking.userName  +"</td><td>"+ booking.checkInDate +"</td><td>"+ booking.checkOutDate
                                +"</td><td>$"+ booking.finalCharges +"</td><td>"+ booking.status +"</td></tr>"
                            )

                        },
                        error:function(err){
                            alert("error while retrieving hotel information" + err)
                        }
                    })
                })
            })

            $("#completed").click(function(){
                $(this).parent().css("background-color","orange")
                $("#canceled").parent().css("background-color", "white")
                $("#upcoming").parent().css("background-color", "white")

                $("#completed-body").show()
                $("#upcoming-body").hide()
                $("#canceled-body").hide()

                let completedBookings = []

                $("#completed-table tr:not(:first)").remove()

                $.ajax({
                    type:"GET",
                    url:"http://localhost:8282/getCompletedBookings",
                    async: false,
                    success:function(response){

                        $.each(response, function(index,booking){
                            completedBookings.push(booking);
                        })
                    },
                    error:function(err){
                        alert("error while retrieving data completed bookings " + err );
                    }
                })

                $.each(completedBookings, function(index, booking){

                    $.ajax({
                        type: "GET",
                        url:"http://localhost:8282/searchHotelById/"+booking.hotelId,
                        async: false,
                        success:function(response){

                            $("#completed-table").append(
                                "<tr><td>" + response.hotelName +"</td><td>"+ booking.roomType  +"</td><td>"+ booking.userName  +"</td><td>"+ booking.checkInDate +"</td><td>"+ booking.checkOutDate
                                +"</td><td>$"+ booking.finalCharges +"</td><td>"+ booking.status +"</td><td><button class='review btn btn-primary' data-hotelname='"+response.hotelName+"' data-bookingId='"+booking.bookingId+"'>Review</button></td></tr>"
                            )

                        },
                        error:function(err){
                            alert("error while retrieving hotel information" + err)
                        }
                    })
                })

                $(".review").each(function(index,element){
                    $(element).click(function(){

                        let bookingId = $(element).attr("data-bookingId")
                        let hotelName = $(element).attr("data-hotelname")

                        $("#modal_bookingId").val(bookingId);
                        $("#modal_hotelName").val(hotelName);

                        $("#myModal").modal('show')
                    })
                })


            })

            $("#submitReview").click(function(){

                let bookingId = $("#modal_bookingId").val()

                let text = $("#modal_text").val()
                let serviceRating = $("#modal_service").val()
                let amenitiesRating= $("#modal_amenity").val()
                let bookingProcessRating = $("#modal_process").val()
                let wholeExpRating = $("#modal_experience").val()

                let sumOfRatings = parseInt(serviceRating) + parseInt(amenitiesRating) + parseInt(bookingProcessRating) + parseInt(wholeExpRating);
                let overallRating = sumOfRatings/4;

                let booking = {}

                $.ajax({
                    type: "GET",
                    url:"http://localhost:8282/getBooking/"+bookingId,
                    async: false,
                    success:function(response){
                        booking = response;
                    },
                    error:function(err){
                        alert("error while retrieving booking information" + err)
                    }
                })

                let review = { booking, text,serviceRating,amenitiesRating,bookingProcessRating,wholeExpRating,overallRating }

                $.ajax({
                    type:"POST",
                    url:"http://localhost:8282/saveReview",
                    dataType:"json",
                    contentType:"application/json",
                    data: JSON.stringify(review),
                    async: false,
                    success: function(response){
                        alert("successfully left the review")
                        window.location.replace("home")
                    },
                    error: function(err){
                        alert("you have already left the review " + err)
                    }

                })

            })

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
<div align="center">
    <div class="row">
        <div class="col">
            <a id="upcoming" href="#" style="font-size:25px ">UPCOMING</a>
        </div>
        <div class="col">
            <a id="completed" href="#" style="font-size:25px ">COMPLETED</a>
        </div>
        <div class="col">
            <a id="canceled" href="#" style="font-size:25px ">CANCELED</a>
        </div>
    </div>
</div>
<div id="upcoming-body" align="center">

    <table class="table table-light table-striped-column" id="upcoming-table">
        <tr>
            <th>HOTEL NAME</th>
            <th>ROOM TYPE</th>
            <th>USER</th>
            <th>CheckInDate</th>
            <th>CheckOutDate</th>
            <th>Final Price</th>
            <th>Status</th>
            <th>Action</th>
        </tr>
    </table>

</div>

<div id="completed-body" align="center">

    <table class="table table-light table-striped-column" id="completed-table">
        <tr>
            <th>HOTEL NAME</th>
            <th>ROOM TYPE</th>
            <th>USER</th>
            <th>CheckInDate</th>
            <th>CheckOutDate</th>
            <th>Final Price</th>
            <th>Status</th>
            <th>Action</th>
        </tr>
    </table>
</div>

<div id="canceled-body" align="center">

    <table class="table table-light table-striped-column" id="canceled-table">
        <tr>
            <th>HOTEL NAME</th>
            <th>ROOM TYPE</th>
            <th>USER</th>
            <th>CheckInDate</th>
            <th>CheckOutDate</th>
            <th>Final Price</th>
            <th>Status</th>
        </tr>
    </table>
</div>

<div class="modal" id="myModal">
    <div class="modal-dialog">
        <div class="modal-content">

            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">Review Hotel</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>

            <!-- Modal body -->
            <div class="modal-body">
                <div class="col">
                    <input class="form-control" type="hidden" id="modal_hotelId"/>
                    <input class="form-control" type="hidden" id="modal_bookingId"/>
                    Hotel Name: <input readonly="true" class="form-control" type="text" id="modal_hotelName"/>
                    Rating Service 1 - 5 <input class="form-control"  type="number" id="modal_service"/>
                    Rating Amenities 1 - 5 <input class="form-control" type="number" id="modal_amenity"/>
                    Rating Booking Process 1 - 5 <input class="form-control" type="number" id="modal_process"/>
                    Rating Whole Experience 1 - 5 <input class="form-control" type="number" id="modal_experience"/>
                    Comment: <input class="form-control" type="textarea" id="modal_text"/>
                    <input style="margin-top:25px" class="btn btn-searchHotelRooms form-control btn-primary" type="button" id="submitReview" value="Submit"/>
                </div>
            </div>

            <!-- Modal footer -->
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
            </div>

        </div>
    </div>
</div>
</body>
</html>