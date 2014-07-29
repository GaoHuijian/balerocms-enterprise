/**
 * Balero CMS JavaScript
 * Mobile System File
 *
 * http://www.balerocms.com
 * @author Anibal Gomez <anibalgomez@icloud.com>
 */
    // Save CKE Editor Content
    // InTo Server Data
function content(divName, id) {
    // Load mobile data
    var data =  $("#" + divName).html();
    // Variable Datas Has Content
    document.getElementById("dataContainer").value = data;
    document.getElementById("id").value = id;
}
function Content_Click() {
    // Load mobile data
    var dataTitle =  $("#editableTitle").html();
    var dataContent = $("#editableContent").html();
    // Save Div Content into Hidden Field
    document.getElementById("name").value = dataTitle;
    document.getElementById("content").value = dataContent;
}
function Full_Click() {
    var dataContent  =  $("#editableContent").html();
    var dataFull =  $("#editableFull").html();
    document.getElementById("content").value = dataContent;
    document.getElementById("full").value = dataFull;
}
// Footer container
function footer(fid) {
    var data =  $("#editable-footer").html();
    // Save Div Content into Hidden Field
    // Variable Datas Has Content
    document.getElementById("fContainer").value = data;
    document.getElementById("fid").value = fid;
}
function getSlider() {
    var current = slider.getCurrentSlide();
    document.getElementById("sliderContainer").value = current;
}
function getDefaultSlider() {
    var current = slider.getCurrentSlide();
    document.getElementById("defaultSliderContainer").value = current;
}
// Hide and show Site Name because slider
// divs are disabled When menu's show,
// so it will fix the issue
var flag = 0;
$('.navbar-toggle').click(function() {
    flag = flag+1;
    //alert(flag);
    if (flag == 1) $("#site-name").hide();
    if (flag == 2) {
        $("#site-name").show();
        flag = 0;
    }
});
// Remove one plugin.
// http://docs.ckeditor.com/#!/
// guide/dev_howtos_basic_configuration
CKEDITOR.config.removePlugins = 'about, pastefromword, pastetext, undo, clipboard, scayt, wsc, removeformat';