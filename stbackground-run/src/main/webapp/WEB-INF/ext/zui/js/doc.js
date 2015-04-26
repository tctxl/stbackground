$(function()
{
    /* set navbar */
    var $header = $('body > header');
    $header.load('dashboard/menus.html', function()
    {
        if(!$('#navbar').length)
        {
            $('body').addClass('without-navbar');
            return;
        }

        var tab = $header.data('tab') || 'index',
            $navbarCollapse = $('#navbar-collapse');

        if(tab != 'index')
        {
            var $navTab = $navbarCollapse.find('[data-tab="' + tab + '"]');
            $navTab.removeClass('collapsed');
            document.title = $navTab.find('.nav-heading').text() + ' - ' + document.title;
        }

        $navbarCollapse.find('.nav > li > a').each(function()
        {
            var $this = $(this);
            var href = $this.attr('href');
            var target = href.substring(href.indexOf('#'), href.length);
            $this.attr('data-target', target).attr('href', $this.attr('href'));
        });

        $('body').addClass('with-navbar').scrollspy({target: '#navbar-collapse'});


        // navbar collapse
        $navbarCollapse.find('.nav > .nav-heading').click(function()
        {
            var $nav = $(this).closest('.nav');
            if($nav.hasClass('collapsed'))
            {
                $('.navbar-collapsed .nav').not($nav).children('li:not(.nav-heading)').slideUp('fast', function(){
                    $(this).closest('.nav').addClass('collapsed');
                });
                if($(window).width() < 767)
                {
                }
                $nav.removeClass('collapsed').children('li:not(.nav-heading)').slideDown('fast');
            }
            else
            {
                $nav.children('li:not(.nav-heading)').slideUp('fast', function(){$nav.addClass('collapsed');});
            }
        });
    });

});