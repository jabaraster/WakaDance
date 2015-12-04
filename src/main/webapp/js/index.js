$(function() {
  $('button.downloaded').click(function() {
    var id = $(this).attr('data-record-id') - 0;
    console.log($(this).attr('data-record-id'));
    $.post('/file', { 'id': id }, function() {
      console.log(arguments);
      $('.send-state-' + id).text('DOWNLOADED');
    });
  });
});