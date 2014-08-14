/**
 * Balero CMS JavaScript
 * Desktop System File
 *
 * http://www.balerocms.com
 * @author Anibal Gomez <anibalgomez@icloud.com>
 */
// Footer container
function footer(fid) {
    // Save Div Content into Hidden Field
    var editor = CKEDITOR.instances["editable-footer"];
    // CKE Editor Method
    var data = editor.getData();
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