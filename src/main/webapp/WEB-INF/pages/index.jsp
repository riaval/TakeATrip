<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<form>
    <div class="form-group">
        <label>Choose City</label> <br>
        <input class="typeahead form-control" type="text" placeholder="City">
    </div>
</form>

<div class="fields">
    <div class="field full">
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
            <input type="number" class="form-control">
        </div>
    </div>

    <div class="field empty">
        <span class="glyphicon glyphicon-plus-sign" aria-hidden="true"></span>
    </div>
</div>

<div class="field full" id="template">
    <div class="form-group">
        <label>Direction City</label> <br>
        <input class="typeahead-new form-control" type="text" placeholder="City">
    </div>

    <div class="form-group">
        <label>Transport</label> <br>
        <select class="selectpicker">
            <option value="PLANE">Plane</option>
            <option value="BUS">Bus</option>
            <option value="TRAIN">Train</option>
        </select>
    </div>

    <div class="form-group">
        <label>Days</label> <br>
        <input type="number" class="form-control">
    </div>
</div>

<script>

    availableCities = [];
    currentCity = null;

    $('.typeahead-new').typeahead({ hint: false, highlight: false },{
        display: 'name',
        templates: {
            empty: [
                '<div class="empty-message">',
                'unable to find any cities that match the current query',
                '</div>'
            ].join('\n'),
            suggestion: function(data) {
                return '<p><strong>' + data.name + '</strong> - ' + data.country + '</p>';
            }
        },
        source: function (query, process) {
            process(availableCities)
        }
    }).on('typeahead:selected', function (ev, datum) {
        $.ajax({
            type: 'GET',
            dataType: 'json',
            url: 'http://localhost:8080/TakeATrip/cities/price',
            data: {
                cityStart: currentCity.id,
                cityFinish: datum.id
            }
        }).done(function (response) {
            console.log(response)

            response.type.forEach(function (type, index) {
                $('.selectpicker').append(
                        $('<option>').val(type).text(capitalizeFirstLetter(type))
                ).removeAttr('disabled');
                $('.selectpicker').selectpicker('render');
            });

        });
    });

    $('.typeahead').typeahead({
        hint: false,
        highlight: false
    }, {
        display: 'name',
        templates: {
            empty: [
                '<div class="empty-message">',
                'unable to find any cities that match the current query',
                '</div>'
            ].join('\n'),
            suggestion: function(data) {
                return '<p><strong>' + data.name + '</strong> - ' + data.country + '</p>';
            }
        },
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

                //                console.log(response)
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
            //            console.log(response)
        });
        $('.fields').show();
    });

    $('.field.empty').click(function (ev) {
        var $el = $('#template').clone().removeAttr('id');
        $('.fields .field.full').last().after($el);
        if ($('.fields .field.full').length >= 4) {
            $(ev.currentTarget).hide()
        }
    });

    $('.selectpicker').selectpicker();

    function capitalizeFirstLetter(string) {
        return string.charAt(0).toUpperCase() + string.slice(1);
    }
</script>