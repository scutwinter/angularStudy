Vue.directive('clickoutsize',{
    bind: function (el,binding,vnode) {
        function documentHandler(e) {
            //获取修饰符
            var escSwitch = (binding.modifiers && binding.modifiers.esc);
            if(el.contains(e.target)){
                if(escSwitch && e.key=='Escape') {
                }else
                    return false;
            }
            if(binding.expression || e.key==='Escape'){
                //用来执行当前上下文methods中指定的函数
                binding.value();
            }
        }
        // function documentKeyHandler(e) {
        //     if(e.key==='Escape'){
        //         //用来执行当前上下文methods中指定的函数
        //         binding.value();
        //     }
        // }
        el.__vueClickOutSide__ = documentHandler;
        document.addEventListener('click',documentHandler);
        document.addEventListener('keydown',documentHandler);

    },
    unbind: function (el,binding) {
        document.removeEventListener('click',el.__vueClickOutSide__);
        document.removeEventListener('keydown',el.__vueClickOutSide__);

        delete el.__vueClickOutSide__;

    }

})
