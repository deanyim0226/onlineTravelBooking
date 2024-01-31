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

            $("#canceled-body").hide()
            $("#upcoming-body").hide()
            $("#completed-body").hide()

            $("#upcoming").click(function(){
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
                                "<tr><td>" + response.hotelName +"</td><td>"+ booking.roomType  +"</td><td>"+ booking.checkInDate +"</td><td>"+ booking.checkOutDate
                                +"</td><td>$"+ booking.finalCharges +"</td><td>"+ booking.status +"</td><td><button class='cancel' data-bookingId='"+booking.bookingId+"'>Cancel</button></td></tr>"
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
                                "<tr><td>" + response.hotelName +"</td><td>"+ booking.roomType  +"</td><td>"+ booking.checkInDate +"</td><td>"+ booking.checkOutDate
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
                                "<tr><td>" + response.hotelName +"</td><td>"+ booking.roomType  +"</td><td>"+ booking.checkInDate +"</td><td>"+ booking.checkOutDate
                                +"</td><td>$"+ booking.finalCharges +"</td><td>"+ booking.status +"</td><td><button class='review' data-bookingId='"+booking.bookingId+"'>Review</button></td></tr>"
                            )

                        },
                        error:function(err){
                            alert("error while retrieving hotel information" + err)
                        }
                    })
                })

                $(".review").each(function(index,element){
                    $(element).click(function(){
                        alert($(element).attr("data-bookingId"))

                        $("#myModal").toggle()
                    })
                })


            })

            $("#submitReview").click(function(){
                //make an api call to save review in the database
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
                <li class = "nav-item"><a class="btn btn-dark dropdown"  href="userForm" >USER</a></li>
                <s:authorize access="hasAuthority('Admin')">
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
    <table border="1">
        <th>
            <tr><a id="upcoming" href="#">UPCOMING</a> | </tr>
            <tr><a id="completed" href="#">COMPLETED</a> | </tr>
            <tr><a id="canceled" href="#">CANCELED</a></tr>
        </th>
    </table>
</div>
<div id="upcoming-body" align="center">
    <h1>UPCOMING</h1>
    <table id="upcoming-table">
        <tr>
            <th>HOTEL NAME</th>
            <th>ROOM TYPE</th>
            <th>CheckInDate</th>
            <th>CheckOutDate</th>
            <th>Final Price</th>
            <th>Status</th>
            <th>Action</th>
        </tr>
    </table>

</div>

<div id="completed-body" align="center">
    <h1>COMPLETED</h1>
    <table id="completed-table">
        <tr>
            <th>HOTEL NAME</th>
            <th>ROOM TYPE</th>
            <th>CheckInDate</th>
            <th>CheckOutDate</th>
            <th>Final Price</th>
            <th>Status</th>
            <th>Action</th>
        </tr>
    </table>
</div>

<div id="canceled-body" align="center">
    <h1>CANCELED</h1>
    <table id="canceled-table">
        <tr>
            <th>HOTEL NAME</th>
            <th>ROOM TYPE</th>
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
                    Hotel Name: <input readonly="true" class="form-control" type="text" id="modal_hotelName"/>
                    Comment: <input class="form-control" type="text" id="modal_text"/>
                    Service: <input class="form-control" type="number" id="modal_service"/>
                    Amenities: <input class="form-control" type="number" id="modal_amenity"/>
                    <input id="input-id" name="input-name" type="number" class="rating" min=1 max=5 step=0.5 data-size="lg" value='0'>

                    Booking-Process: <input class="form-control" type="number" id="modal_process"/>
                    Whole Experience: <input class="form-control" type="number" id="modal_experience"/>
                    OverallRating: <input class="form-control" type="number" id="modal_overal"/>
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