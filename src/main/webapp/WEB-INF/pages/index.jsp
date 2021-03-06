<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div>
    <div class="form-group main-query">
        <%--<label>Choose City</label> <br>--%>
        <input class="typeahead form-control" type="text" placeholder="Departure city">
        <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
    </div>
</div>

<div class="fields">
    <div class="field empty">
        <span class="glyphicon glyphicon-plus-sign" aria-hidden="true"></span>
    </div>
</div>

<div class="motivation">
    <h1>Compare your trips!</h1>
    <p class="lead">Start using by piking departure city in the input above.</p>
</div>

<div class="field full" id="template">
    <div class="form-group">
        <label>Direction City</label> <br>
        <input class="typeahead-new form-control" type="text" placeholder="City">
    </div>

    <div class="form-group">
        <label>Transport</label> <br>
        <select class="selectpicker" disabled>
            <%--<option value="PLANE">Plane</option>--%>
            <%--<option value="BUS">Bus</option>--%>
            <%--<option value="TRAIN">Train</option>--%>
        </select>
    </div>

    <div class="form-group">
        <label>Days</label> <br>
        <input type="number" min="1" class="form-control days">
    </div>

    <div class="checkout">
        <div class="price transfer">
            <strong>Transfer price: </strong>
            <span></span>
        </div>
        <div class="price living">
            <strong>Living price: </strong>
            <span></span>
        </div>
        <div class="price food">
            <strong>Food price: </strong>
            <span></span>
        </div>

        <div class="total">
            <strong>Total: </strong>
            <span></span>
        </div>
    </div>
</div>

<script>
    availableCities = [];
    currentCity = null;

    $('.glyphicon.glyphicon-remove').click(function () {
        $('.typeahead').val('');
        $('.fields').hide();
        $('.motivation').show();
    });

    $('.typeahead').typeahead({
        hint: false,
        highlight: false
    }, {
        display: 'name',
        templates: templates(),
        source: function (query, process) {
            return $.ajax({
                type: 'GET',
                dataType: 'json',
                url: 'http://localhost:8080/TakeATrip/cities/suggested',
                data: {
                    city: query
                }
            }).done(function (response) {
                var matches = [];
                $.each(response, function (i, city) {
                    matches.push({value:city.name});
                });

                return process(response);
            });
        }
    }).on('typeahead:selected', function (ev, datum) {
        currentCity = datum;
        $.ajax({
            type: 'GET',
            dataType: 'json',
            url: 'http://localhost:8080/TakeATrip/cities/available',
            data: {
                city: datum.id
            }
        }).done(function (response) {
            window.availableCities = response;
        });
        $('.fields').show();
        $('.motivation').hide();
    });

    $('.field.empty').click(function (ev) {
        var $el = $('#template').clone().removeAttr('id');
        $('.fields .field.empty').before($el);
        if ($('.fields .field.full').length >= 4) {
            $(ev.currentTarget).hide()
        }

        $('.typeahead-new').typeahead({ hint: false, highlight: false },{
            display: 'name',
            templates: templates(),
            source: function (query, process) {
                process(availableCities)
            }
        }).on('typeahead:selected', function (ev, datum) {
            var $field = $(ev.currentTarget).parents('.field.full');
            $.ajax({
                type: 'GET',
                dataType: 'json',
                url: 'http://localhost:8080/TakeATrip/cities/price',
                data: {
                    cityStart: currentCity.id,
                    cityFinish: datum.id
                }
            }).done(function (response) {
                var priceIndex = 0;
                response.type.forEach(function (type, index) {
                    $field.find('.selectpicker').append(
                            $('<option>').val(type).text(capitalizeFirstLetter(type))
                    ).prop('disabled', false);
                    $field.find('.selectpicker').selectpicker('refresh').change(function (ev) {
                        priceIndex = $field.find('.selectpicker option:selected').index();
                        $field.find('.days').val(1);
                        change(1);
                    });
                    $field.find('.bootstrap-select.disabled').remove();
                    $field.find('.checkout').show();

                    $field.find('.days').removeAttr('disabled').val(1).change(function (ev) {
                        var days = $(ev.currentTarget).val();
                        change(days);
                    });
                    change(1);

                    function change(days) {
                        var transfer = +response.price[priceIndex];
                        var living = +response.priceLive * days;
                        var food = +response.priceFood * days;

                        $field.find('.transfer span').html('$' + transfer);
                        $field.find('.living span').html('$' + living);
                        $field.find('.food span').html('$' + food);
                        $field.find('.total span').html('$' + (transfer + living + food));

                        optimazeColours();
                    }

                    function optimazeColours() {
                        optimazeColour($('.fields .price.transfer'));
                        optimazeColour($('.fields .price.living'));
                        optimazeColour($('.fields .price.food'));
                        optimazeColour($('.fields .total'));

                    }

                    function optimazeColour(prices) {
                        var best = {
                            val: 99999,
                            price: []
                        };
                        prices.each(function (index, price) {
                            var nPrice = +$(price).find('span').text().substring(1);
                            if (nPrice < best.val) {
                                best.val = nPrice;
                                best.price = [$(price)]
                            } else if (nPrice == best.val) {
                                best.val = nPrice;
                                best.price.push($(price))
                            }
                        });
                        prices.removeClass('best').addClass('worth');
                        $.each(best.price, function (index, item) {
                            item.removeClass('worth').addClass('best')
                        });
                    }

                });

            });
        });
    });

    $('.field.empty').click();

    function capitalizeFirstLetter(string) {
        return string.charAt(0).toUpperCase() + string.slice(1).toLowerCase();
    }

    function templates() {
        return {
            empty: [
                '<div class="empty-message">',
                'Unable to find any cities that match the current query',
                '</div>'
            ].join('\n'),
            suggestion: function(data) {
                return '<p><strong>' + data.name + '</strong> - ' + data.country + '</p>';
            }
        }
    }
</script>