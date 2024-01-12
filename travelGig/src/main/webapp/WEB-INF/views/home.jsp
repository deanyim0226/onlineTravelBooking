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

            $("#tb1Hotel").on("click",".image",function(){

                let id = $(this).attr("data-id")

                let noRooms = $("#noRooms").val();
                let noGuests = $("#noGuests").val();
                let checkInDate = $("#checkInDate").val();
                let checkOutDate = $("#checkOutDate").val();

                $("#modal_noGuests").val(noGuests);
                $("#modal_noRooms").val(noRooms);
                $("#modal_checkInDate").val(checkInDate);
                $("#modal_checkOutDate").val(checkOutDate);

                //1. make an api call to retrieve hotel name by using hotel Id
                $.ajax({
                    type: "GET",
                    url: "/searchHotelById/" +id,
                    success: function(response){
                        $("#modal_hotelName").val(response.hotelName);
                    }
                })

                //2. make an api call to retrieve hotel room type
                $.ajax({
                    type:"GET",
                    url:"/getHotelRooms",
                    success: function(response){
                        $.each(response,function(key,value){
                            $("#select_roomTypes").append("<option>"+value.description + "</option>")
                        })
                    },
                    error: function (err){
                        alert("something wrong while retrieving hotel rooms")
                    }
                })

                $("#myModal").modal('show')
            })

            $("#searchBtn").click(function(){

                var searchString = $("#searchLocation").val();
                $.get("/searchHotel/"+searchString,
                    function(response){
                    $.each(response,function(index,val){
                        console.log(index)
                        console.log(val)

                        $("#tb1Hotel").append("<tr><td>" +val.hotelName + "</td><td>" +val.address +
                            "</td><td>" + val.averagePrice + "</td><td><img class='image' data-id='"+val.hotelId+"' src='"+val.imageURL+"' width='300' height='300'></img></td><td>" +
                            val.starRating + "</td></tr>")
                    })
                })
            });

            $("#filterBtn").click(function(){
                $("#tb1Hotel tr").not(":first").show();

                var selectedPrice = parseInt($("#priceValue").text());
                var selectedRating = new Map();

                if($("#1_star_rating").is(":checked")){
                    selectedRating.set($("#1_star_rating").val(), $("#1_star_rating").val());
              //    selectedRating[$("#1_star_rating").val()] = $("#1_star_rating").val()
                }

                if($("#2_star_rating").is(":checked")){
                    selectedRating.set($("#2_star_rating").val(), $("#2_star_rating").val());
                }

                if($("#3_star_rating").is(":checked")){
                    selectedRating.set($("#3_star_rating").val(), $("#3_star_rating").val());
                }

                if($("#4_star_rating").is(":checked")){
                    selectedRating.set($("#4_star_rating").val(), $("#4_star_rating").val());
                }

                if($("#5_star_rating").is(":checked")){
                    selectedRating.set($("#5_star_rating").val(), $("#5_star_rating").val());
                }


                $("#tb1Hotel tr").not(":first").each(function(index,val){
                    var price = $(this).children("td").eq("2").text()
                    var rating = $(this).children("td").eq("4").text()

                    if(price > selectedPrice){
                        $(this).hide();
                    }

                    if(selectedRating.size == 0){

                    }else if(!selectedRating.has(rating)){
                        $(this).hide();
                    }




                })
            })




        })
    </script>
</head>
<body>
<div class="container" style="margin-left:100px">
    <h1>Welcome to Travel Gig</h1>
    <h2>Search your desired hotel</h2>
</div>

<div class="container border rounded" style="margin:auto;padding:50px;margin-top:50px;margin-bottom:50px">
    <h3>Narrow your search results</h3>
    <div class="form-row">
        <div class="col-3">
            Hotel/City/State/Address <input class="form-control" type="text" id="searchLocation" name="searchLocation"/>
        </div>
        <div class="col-2">
            No. Rooms: <input class="form-control" type="number" id="noRooms" name="noRooms"/>
        </div>
        <div class="col-2">
            No. Guests: <input class="form-control" type="number" id="noGuests" name="noGuests"/>
        </div>
        <div class="col">
            Check-In Date: <input type="date" id="checkInDate" name="checkInDate"/>
        </div>
        <div class="col">
            Check-Out Date: <input type="date" id="checkOutDate" name="checkOutDate"/>
        </div>
        <input class="btn-sm btn-primary" type="button" id="searchBtn" value="SEARCH"/>
    </div>
</div>

<div class="row">
    <div class="col-2 border rounded" style="margin-left:50px;padding:25px">

        <br>
        <!--  Star Rating:
        <select class="form-control" id="filter_starRating">
            <option value=0>Select</option>
            <option value=1>1</option>
            <option value=2>2</option>
            <option value=3>3</option>
            <option value=4>4</option>
            <option value=5>5</option>
        </select><br>-->

        Star Rating:<br>
        <div class="form-check-inline">
            <label class="form-check-label">
                <input type="checkbox" class="star_rating form-check-input" id="1_star_rating" value=1>1
            </label>
        </div>
        <div class="form-check-inline">
            <label class="form-check-label">
                <input type="checkbox" class="star_rating form-check-input" id="2_star_rating" value=2>2
            </label>
        </div>
        <div class="form-check-inline">
            <label class="form-check-label">
                <input type="checkbox" class="star_rating form-check-input" id="3_star_rating" value=3>3
            </label>
        </div>
        <div class="form-check-inline">
            <label class="form-check-label">
                <input type="checkbox" class="star_rating form-check-input" id="4_star_rating" value=4>4
            </label>
        </div>
        <div class="form-check-inline">
            <label class="form-check-label">
                <input type="checkbox" class="star_rating form-check-input" id="5_star_rating" value=5>5
            </label>
        </div><br><br>

        Range:
        <div class="slidecontainer">
            <input type="range" min="1" max="5000" value="5000" class="slider" id="priceRange">
            <p>Price: $<span id="priceValue"></span></p>
        </div>

        <div class="form-check">
            <input type="checkbox" class="hotel_amenity form-check-input" id="amenity_parking" value="PARKING"/>
            <label class="form-check-label" for="amenity_parking">Parking</label><br>

            <input type="checkbox" class="hotel_amenity form-check-input" id="amenity_checkin_checkout" value="CHECK-IN & CHECK-OUT TIMES"/>
            <label class="form-check-label" for="amenity_checkin_checkout">Check-In & Check-Out Times</label><br>

            <input type="checkbox" class="hotel_amenity form-check-input" id="amenity_breakfast" value="BREAKFAST"/>
            <label class="form-check-label" for="amenity_breakfast">Breakfast</label><br>

            <input type="checkbox" class="hotel_amenity form-check-input" id="amenity_bar_lounge" value="BAR OR LOUNGE"/>
            <label class="form-check-label" for="amenity_bar_lounge">Bar / Lounge</label><br>

            <input type="checkbox" class="hotel_amenity form-check-input" id="amenity_fitness_center" value="FITNESS CENTER"/>
            <label class="form-check-label" for="amenity_fitness_center">Fitness Center</label><br>
        </div>

        <input style="margin-top:25px" class="btn btn-primary" type="button" id="filterBtn" value="FILTER"/>
    </div>


    <div class="col-7 border rounded" style="margin-left:50px;">
        <div style='text-align:center;font-size:20px;font-family:"Trebuchet MS", Helvetica, sans-serif'>List of Hotels:</div>

        <div id="listHotel">
            <table id="tb1Hotel" border="1">
                <tr><th>Name</th>
                    <th>Address</th>
                    <th>Price</th>
                    <th>Image</th>
                    <th>Rating</th>
                </tr>
            </table>
        </div>

    </div>
</div>

<div class="modal" id="myModal">
    <div class="modal-dialog">
        <div class="modal-content">

            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">Search Hotel Rooms</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>

            <!-- Modal body -->
            <div class="modal-body">
                <div class="col">
                    <input class="form-control" type="hidden" id="modal_hotelId"/>
                    Hotel Name: <input readonly="true" class="form-control" type="text" id="modal_hotelName"/>
                    No. Guests: <input class="form-control" type="number" id="modal_noGuests"/>
                    Check-In Date: <input class="form-control" type="date" id="modal_checkInDate"/>
                    Check-Out Date: <input class="form-control" type="date" id="modal_checkOutDate"/>
                    Room Type:
                    <select class="form-control" id="select_roomTypes">
                    </select>
                    No. Rooms: <input class="form-control" type="number" id="modal_noRooms"/>
                    <input style="margin-top:25px" class="btn btn-searchHotelRooms form-control btn-primary" type="button" id="" value="SEARCH"/>
                </div>

            </div>

            <!-- Modal footer -->
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
            </div>

        </div>
    </div>
</div>

<div class="modal" id="hotelRoomsModal">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">

            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">Are these details correct?</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>

            <!-- Modal body -->
            <div class="modal-body" id="hotelRooms_modalBody">

            </div>

            <!-- Modal footer -->
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
            </div>

        </div>
    </div>
</div>

<div class="modal" id="bookingHotelRoomModal">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">

            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title"></h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>

            <!-- Modal body -->
            <div class="modal-body" id="bookingRoom_modalBody">
                <div class="col">
                    <div><input class="form-control" type="hidden" id="booking_hotelId"/></div>
                    <div><input class="form-control" type="hidden" id="booking_hotelRoomId"/></div>
                    <div>Hotel Name: <input readonly="true" class="form-control" type="text" id="booking_hotelName"/></div>
                    <div>Customer Mobile: <input class="form-control" type="text" id="booking_customerMobile"/></div>
                    <div id="noGuestsDiv">No. Guests: <input readonly="true" class="form-control" type="number" id="booking_noGuests"/></div>
                    <div>No. Rooms: <input readonly="true" class="form-control" type="number" id="booking_noRooms"/></div>
                    <div>Check-In Date: <input readonly="true" class="form-control" type="text" id="booking_checkInDate"/></div>
                    <div>Check-Out Date: <input readonly="true" class="form-control" type="text" id="booking_checkOutDate"/></div>
                    <div>Room Type: <input readonly="true" class="form-control" type="text" id="booking_roomType"/></div>
                    <div>Discount: $<span id="booking_discount"></span></div>
                    <div>Total Price: $<span id="booking_price"></span></div>
                    <div style='margin-top:20px'>
                        <button class='btn-confirm-booking btn btn-primary'>Confirm Booking</button>
                        <button class='btn btn-primary'>Edit</button>
                    </div>
                </div>
            </div>

            <!-- Modal footer -->
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
            </div>

        </div>
    </div>
</div>

<script>
    var slider = document.getElementById("priceRange");
    var output = document.getElementById("priceValue");
    output.innerHTML = slider.value;
    slider.oninput = function() {
        output.innerHTML = this.value;
    }
</script>
</body>
</html>