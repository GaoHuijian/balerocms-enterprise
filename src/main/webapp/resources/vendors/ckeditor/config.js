/**
 * @license Copyright (c) 2003-2013, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.html or http://ckeditor.com/license
 */

// ckeditor/config.js
CKEDITOR.editorConfig = function( config ) {
    // CKE Editor for Balero CMS
    // Browser View
    config.filebrowserBrowseUrl = '/upload/browser';
    // Upload Controller
    config.filebrowserUploadUrl = '/upload/picture';
    // Make it responsive
    // Remove Width And Height
    config.disallowedContent = 'img[width,height]';
    // Extra plugins
    // Add Here
    config.extraPlugins = 'sourcedialog';
};
