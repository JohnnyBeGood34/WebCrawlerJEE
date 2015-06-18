/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(function(){
    panelVisibility();
    hidePanel();
    showPanel();
});

function panelVisibility(){
    $('.hidePanel').hide();
    $('.showPanel').show();
    $('.hidePanel').parent('.panel-title').parent('.panel-heading').siblings('.panel-body').hide();
}

function hidePanel() {
    $('.hidePanel').click(function () {
        $(this).parent('.panel-title').parent('.panel-heading').siblings('.panel-body').hide('fast');
        $(this).hide();
        $(this).siblings('.showPanel').show();
    });
}

function showPanel() {
    $('.showPanel').click(function () {
        $(this).parent('.panel-title').parent('.panel-heading').siblings('.panel-body').show('fast');
       $(this).hide();
        $(this).siblings('.hidePanel').show();
    });
}