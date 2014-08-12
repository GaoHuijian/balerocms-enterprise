

<!DOCTYPE html>
<!--
Copyright (c) 2003-2014, CKSource - Frederico Knabben. All rights reserved.
For licensing, see LICENSE.md or http://ckeditor.com/license
-->
<html>
<head>
	<meta charset="utf-8">
	<title>Massive inline editing &mdash; CKEditor Sample</title>
	<script src="../ckeditor.js"></script>
	<script>

		// This code is generally not necessary, but it is here to demonstrate
		// how to customize specific editor instances on the fly. This fits well
		// this demo because we have editable elements (like headers) that
		// require less features.

		// The "instanceCreated" event is fired for every editor instance created.
		CKEDITOR.on( 'instanceCreated', function( event ) {
			var editor = event.editor,
				element = editor.element;

			// Customize editors for headers and tag list.
			// These editors don't need features like smileys, templates, iframes etc.
			if ( element.is( 'h1', 'h2', 'h3' ) || element.getAttribute( 'id' ) == 'taglist' ) {
				// Customize the editor configurations on "configLoaded" event,
				// which is fired after the configuration file loading and
				// execution. This makes it possible to change the
				// configurations before the editor initialization takes place.
				editor.on( 'configLoaded', function() {

					// Remove unnecessary plugins to make the editor simpler.
					editor.config.removePlugins = 'colorbutton,find,flash,font,' +
						'forms,iframe,image,newpage,removeformat,' +
						'smiley,specialchar,stylescombo,templates';

					// Rearrange the layout of the toolbar.
					editor.config.toolbarGroups = [
						{ name: 'editing',		groups: [ 'basicstyles', 'links' ] },
						{ name: 'undo' },
						{ name: 'clipboard',	groups: [ 'selection', 'clipboard' ] },
						{ name: 'about' }
					];
				});
			}
		});

	</script>
	<link href="sample.css" rel="stylesheet">
	<style>

		/* The following styles are just to make the page look nice. */

		/* Workaround to show Arial Black in Firefox. */
		@font-face
		{
			font-family: 'arial-black';
			src: local('Arial Black');
		}

		*[contenteditable="true"]
		{
			padding: 10px;
		}

		#container
		{
			width: 960px;
			margin: 30px auto 0;
		}

		#header
		{
			overflow: hidden;
			padding: 0 0 30px;
			border-bottom: 5px solid #05B2D2;
			position: relative;
		}

		#headerLeft,
		#headerRight
		{
			width: 49%;
			overflow: hidden;
		}

		#headerLeft
		{
			float: left;
			padding: 10px 1px 1px;
		}

		#headerLeft h2,
		#headerLeft h3
		{
			text-align: right;
			margin: 0;
			overflow: hidden;
			font-weight: normal;
		}

		#headerLeft h2
		{
			font-family: "Arial Black",arial-black;
			font-size: 4.6em;
			line-height: 1.1;
			text-transform: uppercase;
		}

		#headerLeft h3
		{
			font-size: 2.3em;
			line-height: 1.1;
			margin: .2em 0 0;
			color: #666;
		}

		#headerRight
		{
			float: right;
			padding: 1px;
		}

		#headerRight p
		{
			line-height: 1.8;
			text-align: justify;
			margin: 0;
		}

		#headerRight p + p
		{
			margin-top: 20px;
		}

		#headerRight > div
		{
			padding: 20px;
			margin: 0 0 0 30px;
			font-size: 1.4em;
			color: #666;
		}

		#columns
		{
			color: #333;
			overflow: hidden;
			padding: 20px 0;
		}

		#columns > div
		{
			float: left;
			width: 33.3%;
		}

		#columns #column1 > div
		{
			margin-left: 1px;
		}

		#columns #column3 > div
		{
			margin-right: 1px;
		}

		#columns > div > div
		{
			margin: 0px 10px;
			padding: 10px 20px;
		}

		#columns blockquote
		{
			margin-left: 15px;
		}

		#tagLine
		{
			border-top: 5px solid #05B2D2;
			padding-top: 20px;
		}

		#taglist {
			display: inline-block;
			margin-left: 20px;
			font-weight: bold;
			margin: 0 0 0 20px;
		}

	</style>
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
</head>
<body>

<?php

echo "value: <b>" . $_POST['dataContainer'] . "</b>";

?>

<form method="post">
		<div id="editor1" contenteditable="true">
			content 1
		</div>
		<input type="hidden" name="dataContainer" class="dataContainer">
		<input type="submit" id="submit1">
		<script>
			$( "#submit1" ).click(function() {
				var data = CKEDITOR.instances.editor1.getData();
				$(".dataContainer").val(data);
				alert(data);
			});
		</script>
</form>

<form method="post">
		<div id="editor2" contenteditable="true">
			content 2
		</div>
		<input type="hidden" name="dataContainer" class="dataContainer">
		<input type="submit" id="submit2">
		<script>
			$( "#submit2" ).click(function() {
				var data = CKEDITOR.instances.editor2.getData();
				$(".dataContainer").val(data);
				alert(data);
			});
		</script>
</form>

<form method="post">
		<div id="editor3" contenteditable="true">
			content 3
		</div>
		<input type="hidden" name="dataContainer" class="dataContainer">
		<input type="submit" id="submit3">
		<script>
			$( "#submit3" ).click(function() {
				var data = CKEDITOR.instances.editor3.getData();
				$(".dataContainer").val(data);
				alert(data);
			});
		</script>
</form>

</body>
</html>
