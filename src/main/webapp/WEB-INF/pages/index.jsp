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
    <div class="field">
        <div class="form-group">
            <label>Direction City</label> <br>
            <input class="typeahead form-control" type="text" placeholder="City">
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
</div>

<script>

    $('.typeahead').typeahead({
        hint: false,
        highlight: false
    },
    {
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
                url: 'http://localhost:8080/TakeATrip/cities',
                data: {
                    city: query
                }
            }).done(function (response) {

                var matches = [];
                $.each(response, function (i, city) {
                    matches.push({value:city.name});
                });

                console.log(response)
                return process(response);
            });
        }
    }).on('typeahead:selected', function (ev) {
        $('.fields').show();
    });
// .keypress(function(e) {
//        if (e.keyCode == '13') {
//            e.preventDefault();
//            that.searchButtonClickEvent(e)
//        }
//    });

    $('.selectpicker').selectpicker();
</script>