/*
 * File: app/view/XlWeiboDataCatchTab.js
 *
 * This file was generated by Sencha Architect version 2.2.2.
 * http://www.sencha.com/products/architect/
 *
 * This file requires use of the Ext JS 4.2.x library, under independent license.
 * License of Sencha Architect does not include license for Ext JS 4.2.x. For more
 * details see http://www.sencha.com/license or contact license@sencha.com.
 *
 * This file will be auto-generated each and everytime you save your project.
 *
 * Do NOT hand edit this file.
 */

Ext.define('MyApp.view.XlWeiboDataCatchTab', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.XlWeiboDataCatchTab',

    id: 'XlWeiboDataCatchTab',
    layout: {
        type: 'fit'
    },
    title: '舆情信息获取',

    initComponent: function() {
        var me = this;

        Ext.applyIf(me, {
            items: [
                {
                    xtype: 'gridpanel',
                    id: 'XlWeiboGrid',
                    title: '微博列表',
                    forceFit: true,
                    store: 'XlWeiboStore',
                    columns: [
                        {
                            xtype: 'rownumberer',
                            width: 50
                        },
                        {
                            xtype: 'gridcolumn',
                            width: 20,
                            dataIndex: 'id',
                            text: 'id'
                        },
                        {
                            xtype: 'datecolumn',
                            width: 15,
                            dataIndex: 'createdAt',
                            text: 'createdAt'
                        },
                        {
                            xtype: 'gridcolumn',
                            width: 20,
                            dataIndex: 'mid',
                            text: 'mid'
                        },
                        {
                            xtype: 'gridcolumn',
                            dataIndex: 'text',
                            text: 'text'
                        }
                    ],
                    dockedItems: [
                        {
                            xtype: 'pagingtoolbar',
                            dock: 'bottom',
                            width: 360,
                            displayInfo: true,
                            store: 'XlWeiboStore'
                        }
                    ]
                }
            ],
            dockedItems: [
                {
                    xtype: 'toolbar',
                    dock: 'top',
                    items: [
                        {
                            xtype: 'button',
                            handler: function(button, event) {
                                Ext.Msg.confirm('您正在抓取微博数据：', '确认抓取？', getResult);
                                function getResult(confirm)
                                {
                                    console.log('confirm:', confirm);
                                    if (confirm == "yes"){


                                        Ext.Ajax.request({
                                            url:'oauth_xlweibodata',
                                            params:{testparam:'0'},        
                                            success:function(response){
                                                Ext.Msg.alert('success','抓取成功');                         
                                            },
                                            failure:function(response){
                                                Ext.Msg.alert('failure','抓取失败');      
                                            }

                                        });
                                    }   
                                }
                            },
                            text: '微博授权'
                        },
                        {
                            xtype: 'button',
                            handler: function(button, event) {
                                Ext.Msg.confirm('您正在发微博数据：', '确认？', getResult);
                                function getResult(confirm)
                                {
                                    console.log('confirm:', confirm);
                                    if (confirm == "yes"){


                                        Ext.Ajax.request({
                                            url:'send_xlweibodata',
                                            params:{testparam:'0'},        
                                            success:function(response){
                                                Ext.Msg.alert('success','发微博成功');                         
                                            },
                                            failure:function(response){
                                                Ext.Msg.alert('failure','发微博失败');      
                                            }

                                        });
                                    }   
                                }
                            },
                            text: '发微博'
                        },
                        {
                            xtype: 'button',
                            handler: function(button, event) {
                                Ext.Msg.confirm('您正在抓取微博数据：', '确认？', getResult);
                                function getResult(confirm)
                                {
                                    console.log('confirm:', confirm);
                                    if (confirm == "yes"){


                                        Ext.Ajax.request({
                                            url:'catch_xlweibodata',
                                            params:{testparam:'0'},        
                                            success:function(response){
                                                var mystore = Ext.StoreMgr.get('XlWeiboStore'); //获得store对象
                                                mystore.load();
                                                Ext.Msg.alert('success','抓取微博成功');                         
                                            },
                                            failure:function(response){
                                                Ext.Msg.alert('failure','抓取微博失败');      
                                            }

                                        });
                                    }   
                                }
                            },
                            text: '抓取微博'
                        }
                    ]
                }
            ]
        });

        me.callParent(arguments);
    }

});