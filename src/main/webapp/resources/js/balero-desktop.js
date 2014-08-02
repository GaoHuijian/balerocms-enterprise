/**
 * Balero CMS JavaScript
 * Desktop System File
 *
 * http://www.balerocms.com
 * @author Anibal Gomez <anibalgomez@icloud.com>
 */
    // Save CKE Editor Content
    // InTo Server Data
function content(divName, id) {
    // Load desktop data
    var editor = CKEDITOR.instances[divName];
    var data = editor.getData();
    // Variable Datas Has Content
    document.getElementById("dataContainer").value = data;
    document.getElementById("id").value = id;
}
function Content_Click() {
    // Save Div Content into Hidden Field
    var editorTitle = CKEDITOR.instances["editableTitle"];
    // CKE Editor Method
    var dataTitle = editorTitle.getData();
    var editorContent = CKEDITOR.instances["editableContent"];
    // CKE Editor Method
    var dataContent = editorContent.getData();
    document.getElementById("name").value = dataTitle;
    document.getElementById("content").value = dataContent;
}
function Full_Click() {
    // Save Div Content into Hidden Field
    var editorContent = CKEDITOR.instances["editableContent"];
    // CKE Editor Method
    var dataContent = editorContent.getData();
    var editorFull = CKEDITOR.instances["editableFull"];
    // CKE Editor Method
    var dataFull = editorFull.getData();
    document.getElementById("content").value = dataContent;
    document.getElementById("full").value = dataFull;
}
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