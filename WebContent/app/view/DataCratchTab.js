/*
 * File: app/view/DataCratchTab.js
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

Ext.define('MyApp.view.DataCratchTab', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.DataCratchTab',

    id: 'DataCratchTab',
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
                    id: 'newStatusGrid',
                    title: '微博列表',
                    forceFit: true,
                    store: 'newStatusStore',
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
                            store: 'newStatusStore'
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
                                            url:'oauth_weibodata',
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
                                            url:'send_weibodata',
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
                                            url:'catch_weibodata',
                                            params:{testparam:'0'},        
                                            success:function(response){
                                                var mystore = Ext.StoreMgr.get('newStatusStore'); //获得store对象
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