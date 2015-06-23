/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(function() {
    panelVisibility();
    hidePanel();
    showPanel();
    handleClickOnAddToCampaign();
    handleAddAllResults();
});

function handleAddAllResults() {
    $('.addAllResultsToCampaignButton').click(function() {
        
        $(".addToCampaignButton").each(function(index, element) {
            if ($(element).hasClass("btn-primary")) {
                $(element).removeClass("btn-primary");
                $(element).addClass("btn-danger");
                $(element).val("-");
            } else if ($(element).hasClass("btn-danger")) {
                $(element).removeClass("btn-danger");
                $(element).addClass("btn-primary");
                $(element).val("+");
            }
        });
    });
}

function handleClickOnAddToCampaign() {
    $(".addToCampaignButton").click(function() {
        if ($(this).hasClass("btn-primary")) {
            $(this).removeClass("btn-primary");
            $(this).addClass("btn-danger");
            $(this).val("-");
        } else if ($(this).hasClass("btn-danger")) {
            $(this).removeClass("btn-danger");
            $(this).addClass("btn-primary");
            $(this).val("+");
        }
    });
}

function panelVisibility() {
    $('.hidePanel').hide();
    $('.showPanel').show();
    $('.hidePanel').parent('.panel-title').parent('.panel-heading').siblings('.panel-body').hide();
}

function hidePanel() {
    $('.hidePanel').click(function() {
        $(this).parent('.panel-title').parent('.panel-heading').siblings('.panel-body').hide('fast');
        $(this).hide();
        $(this).siblings('.showPanel').show();
    });
}

function showPanel() {
    $('.showPanel').click(function() {
        $(this).parent('.panel-title').parent('.panel-heading').siblings('.panel-body').show('fast');
        $(this).hide();
        $(this).siblings('.hidePanel').show();
    });
}