var cols = [[
  {field: 'id', title: 'ID', width:80, sort: true, fixed: 'left', sort: true},
  {field: 'productName', title: '名称', width:200},
  {field: 'detail', title: '描述', width:380},
  {fixed: 'right', width:178, align:'center', toolbar: '#barDemo'}
]];

layui.use(['laydate', 'form', 'table'], function () {
  var laydate = layui.laydate, form = layui.form, table = layui.table, $ = layui.$;

  // 起始日期
  laydate.render({
    elem: '#start'
  });
  // 结束日期
  laydate.render({
    elem: '#end'
  });

  // 初始化查询
  table.render({
    elem: '#orderList',
    cols: cols,
    data:[]
  });


  // 点击查询
  $("#search").on('click', function () {
    search2();
  });

  //监听工具条
  table.on('tool(orderTable)', function(obj){
    var data = obj.data;
    if(obj.event === 'del'){
      layer.confirm('真的删除行么', function(index){
        // obj.del();
        layer.close(index);
      });
    } else if(obj.event === 'edit'){
      var id = data.id;
      // layer.alert('编辑行：<br>'+ JSON.stringify(data))
      xadmin.myopen('添加用户','product/toProductEditPage?id='+id,600,300)
    }
  });

});

function search2() {
  layui.use(['form', 'table'], function () {
    var form = layui.form, table = layui.table, $ = layui.$;
    var productName = $("#productName").val();
    table.render({
      id: 'orderReload',
      elem: '#orderList',
      url: '/product/queryList',
      page: true,
      loading: true,
      cols: cols,
      limits: [10, 20, 30, 50, 100],
      where: {
        productName:productName
      }
    });
  })
}

