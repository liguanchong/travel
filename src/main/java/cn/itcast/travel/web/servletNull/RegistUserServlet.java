/*
package cn.dongruan.travel.dao.impl;

import cn.dongruan.travel.domain.Category;

import java.util.List;

*/
/**
 * @Author: 东软
 * @Date: 2020-11-15
 *//*

public interface CategoryDao {
    public List<Category> findAll();
}
package cn.dongruan.travel.dao.impl;

        import cn.dongruan.travel.domain.Category;
        import cn.dongruan.travel.util.JDBCUtils;
        import org.springframework.jdbc.core.BeanPropertyRowMapper;
        import org.springframework.jdbc.core.JdbcTemplate;

        import java.util.List;

*/
/**
 * @Author: 东软
 * @Date: 2020-11-15
 *//*

public class CategoryDaoImpl implements CategoryDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<Category> findAll() {
        String sql = "select * from tab_category ";
        return template.query(sql,new BeanPropertyRowMapper<Category>(Category.class));
    }
    //public List<Category> findAll();
}
package cn.dongruan.travel.dao.impl;

        import cn.dongruan.travel.domain.Favorite;

public interface FavoriteDao {

    */
/**
     * 根据rid和uid查询收藏信息
     * @param rid
     * @param uid
     * @return
     *//*

    public Favorite findByRidAndUid(int rid, int uid);

    */
/**
     * 根据rid 查询收藏次数
     * @param rid
     * @return
     *//*

    public int findCountByRid(int rid);

    */
/**
     * 添加收藏
     * @param i
     * @param uid
     *//*

    void add(int i, int uid);
}
package cn.dongruan.travel.dao.impl;

        import cn.dongruan.travel.domain.Favorite;
        import cn.dongruan.travel.util.JDBCUtils;
        import org.springframework.dao.DataAccessException;
        import org.springframework.jdbc.core.BeanPropertyRowMapper;
        import org.springframework.jdbc.core.JdbcTemplate;

        import java.util.Date;

public class FavoriteDaoImpl implements FavoriteDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public Favorite findByRidAndUid(int rid, int uid) {
        Favorite favorite = null;
        try {
            String sql = " select * from tab_favorite where rid = ? and uid = ?";
            favorite = template.queryForObject(sql, new BeanPropertyRowMapper<Favorite>(Favorite.class), rid, uid);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return favorite;
    }

    @Override
    public int findCountByRid(int rid) {
        String sql = "SELECT COUNT(*) FROM tab_favorite WHERE rid = ?";

        return template.queryForObject(sql, Integer.class,rid);
    }

    @Override
    public void add(int rid, int uid) {
        String sql = "insert into tab_favorite values(?,?,?)";

        template.update(sql,rid,new Date(),uid);
    }
}
package cn.dongruan.travel.dao.impl;

        import cn.dongruan.travel.domain.Route;

        import java.util.List;

*/
/**
 * @Author: 东软
 * @Date: 2020-11-16
 *//*

public interface RouteDao {
    */
/**
     * 根据cid查询总记录数
     *//*

    public int findTotalCount(int cid,String rname);

    */
/**
     * 根据cid，start,pageSize查询当前页的数据集合
     *//*

    public List<Route> findByPage(int cid , int start , int pageSize,String rname);

    */
/**
     * 根据id查询
     * @param rid
     * @return
     *//*

    public Route findOne(int rid);
}
package cn.dongruan.travel.dao.impl;

        import cn.dongruan.travel.domain.Route;
        import cn.dongruan.travel.util.JDBCUtils;
        import org.springframework.jdbc.core.BeanPropertyRowMapper;
        import org.springframework.jdbc.core.JdbcTemplate;

        import java.util.ArrayList;
        import java.util.List;

*/
/**
 * @Author: 东软
 * @Date: 2020-11-16
 *//*

public class RouteDaoImpl implements RouteDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public int findTotalCount(int cid,String rname) {
        //String sql = "select count(*) from tab_route where cid = ?";
        //1.定义sql模板
        String sql = "select count(*) from tab_route where 1=1 ";
        StringBuilder sb = new StringBuilder(sql);

        List params = new ArrayList();//条件们
        //2.判断参数是否有值
        if(cid != 0){
            sb.append( " and cid = ? ");

            params.add(cid);//添加？对应的值
        }

        if(rname != null && rname.length() > 0){
            sb.append(" and rname like ? ");

            params.add("%"+rname+"%");
        }

        sql = sb.toString();


        return template.queryForObject(sql,Integer.class,params.toArray());
    }

    @Override
    public List<Route> findByPage(int cid, int start, int pageSize,String rname) {
        String sql = " select * from tab_route where 1 = 1 ";
        //1.定义sql模板
        StringBuilder sb = new StringBuilder(sql);

        List params = new ArrayList();//条件们
        //2.判断参数是否有值
        if(cid != 0){
            sb.append( " and cid = ? ");

            params.add(cid);//添加？对应的值
        }

        if(rname != null && rname.length() > 0){
            sb.append(" and rname like ? ");

            params.add("%"+rname+"%");
        }
        sb.append(" limit ? , ? ");//分页条件

        sql = sb.toString();

        params.add(start);
        params.add(pageSize);


        return template.query(sql,new BeanPropertyRowMapper<Route>(Route.class),params.toArray());
    }

    @Override
    public Route findOne(int rid) {
        String sql = "select * from tab_route where rid = ?";
        return template.queryForObject(sql,new BeanPropertyRowMapper<Route>(Route.class),rid);
    }


}
package cn.dongruan.travel.dao.impl;

        import cn.dongruan.travel.domain.RouteImg;

        import java.util.List;

*/
/**
 * @Author: 东软
 * @Date: 2020-11-17
 *//*

public interface RouteImgDao {
    public List<RouteImg> findByRid(int rid);
}
package cn.dongruan.travel.dao.impl;

        import cn.dongruan.travel.domain.RouteImg;
        import cn.dongruan.travel.util.JDBCUtils;
        import org.springframework.jdbc.core.BeanPropertyRowMapper;
        import org.springframework.jdbc.core.JdbcTemplate;

        import java.util.List;

*/
/**
 * @Author: 东软
 * @Date: 2020-11-17
 *//*

public class RouteImgDaoImpl implements RouteImgDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());


    @Override
    public List<RouteImg> findByRid(int rid) {
        String sql = "select * from tab_route_img where rid = ? ";
        return template.query(sql,new BeanPropertyRowMapper<RouteImg>(RouteImg.class),rid);
    }
}
package cn.dongruan.travel.dao.impl;

        import cn.dongruan.travel.domain.Seller;

public interface SellerDao {
    */
/**
     * 根据id查询
     * @param id
     * @return
     *//*

    public Seller findById(int id);
}
package cn.dongruan.travel.dao.impl;

        import cn.dongruan.travel.domain.Seller;
        import cn.dongruan.travel.util.JDBCUtils;
        import org.springframework.jdbc.core.BeanPropertyRowMapper;
        import org.springframework.jdbc.core.JdbcTemplate;

public class SellerDaoImpl implements SellerDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());



    @Override
    public Seller findById(int id) {

        String sql = "select * from tab_seller where sid = ? ";
        return template.queryForObject(sql,new BeanPropertyRowMapper<Seller>(Seller.class),id);
    }
}
package cn.dongruan.travel.dao.impl;

        import cn.dongruan.travel.domain.User;

*/
/**
 * @Author: 东软
 * @Date: 2020-11-14
 *//*

public interface UserDao {
    */
/**
     * 根据用户名查询用户信息
     * @param username
     * @return
     *//*

    public User findByUsername(String username);

    */
/**
     * 用户保存
     * @param user
     *//*

    public void save(User user);

    User findByCode(String code);

    void updateStatus(User user);

    User findByUsernameAndPassword(String username, String password);
}
package cn.dongruan.travel.dao.impl;

        import cn.dongruan.travel.domain.User;
        import cn.dongruan.travel.util.JDBCUtils;
        import org.springframework.dao.DataAccessException;
        import org.springframework.jdbc.core.BeanPropertyRowMapper;
        import org.springframework.jdbc.core.JdbcTemplate;

*/
/**
 * @Author: 东软
 * @Date: 2020-11-14
 *//*

public class UserDaoImpl implements UserDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public User findByUsername(String username) {
        User user = null;
        try {
            //1.定义sql
            String sql = "select * from tab_user where username = ?";
            //2.执行sql
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username);
        } catch (Exception e) {

        }

        return user;
    }

    @Override
    public void save(User user) {
        //1.定义sql
        String sql = "insert into tab_user(username,password,name,birthday,sex,telephone,email,status,code) values(?,?,?,?,?,?,?,?,?)";
        //2.执行sql

        template.update(sql,user.getUsername(),
                user.getPassword(),
                user.getName(),
                user.getBirthday(),
                user.getSex(),
                user.getTelephone(),
                user.getEmail(),
                user.getStatus(),
                user.getCode()
        );
    }

    @Override
    public User findByCode(String code) {
        User user = null;
        try {
            String sql = "select * from tab_user where code = ?";

            user = template.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),code);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        return user;
    }

    */
/**
     * 修改指定用户激活状态
     * @param user
     *//*

    @Override
    public void updateStatus(User user) {
        String sql = " update tab_user set status = 'Y' where uid=?";
        template.update(sql,user.getUid());
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        User user = null;
        try {
            //1.定义sql
            String sql = "select * from tab_user where username = ? and password = ?";
            //2.执行sql
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username, password);
        } catch (Exception e) {

        }

        return user;
    }




}
package cn.dongruan.travel.domain;

        import java.io.Serializable;

*/
/**
 * 分类实体类
 *//*

public class Category implements Serializable {

    private int cid;//分类id
    private String cname;//分类名称

    public Category() {
    }

    public Category(int cid, String cname) {
        this.cid = cid;
        this.cname = cname;
    }

    @Override
    public String toString() {
        return "Category{" +
                "cid=" + cid +
                ", cname='" + cname + '\'' +
                '}';
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }
}
package cn.dongruan.travel.domain;

        import java.io.Serializable;

*/
/**
 * 收藏实体类
 *//*

public class Favorite implements Serializable {
    private Route route;//旅游线路对象
    private String date;//收藏时间
    private User user;//所属用户

    */
/**
     * 无参构造方法
     *//*

    public Favorite() {
    }

    */
/**
     * 有参构造方法
     * @param route
     * @param date
     * @param user
     *//*

    public Favorite(Route route, String date, User user) {
        this.route = route;
        this.date = date;
        this.user = user;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
package cn.dongruan.travel.domain;

        import java.io.Serializable;
        import java.util.Objects;

*/
/**
 * 用于封装后端返回前端数据对象
 *//*

public class ResultInfo implements Serializable {
    private boolean flag;//后端返回结果正常为true，发生异常返回false
    private Object data;//后端返回结果数据对象
    private String errorMsg;//发生异常的错误消息

    //无参构造方法
    public ResultInfo() {
    }
    public ResultInfo(boolean flag) {
        this.flag = flag;
    }
    */
/**
     * 有参构造方法
     * @param flag
     * @param errorMsg
     *//*

    public ResultInfo(boolean flag, String errorMsg) {
        this.flag = flag;
        this.errorMsg = errorMsg;
    }
    */
/**
     * 有参构造方法
     * @param flag
     * @param data
     * @param errorMsg
     *//*

    public ResultInfo(boolean flag, Object data, String errorMsg) {
        this.flag = flag;
        this.data = data;
        this.errorMsg = errorMsg;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
package cn.dongruan.travel.domain;

        import java.util.List;

*/
/**
 * 分页对象
 *//*

public class PageBean<T> {

    private int totalCount;//总记录数
    private int totalPage;//总页数
    private int currentPage;//当前页码
    private int pageSize;//每页显示的条数

    private List<T> list;//每页显示的数据集合

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
package cn.dongruan.travel.domain;

        import java.io.Serializable;
        import java.util.List;

*/
/**
 * 旅游线路商品实体类
 *//*

public class Route implements Serializable {

    private int rid;//线路id，必输
    private String rname;//线路名称，必输
    private double price;//价格，必输
    private String routeIntroduce;//线路介绍
    private String rflag;   //是否上架，必输，0代表没有上架，1代表是上架
    private String rdate;   //上架时间
    private String isThemeTour;//是否主题旅游，必输，0代表不是，1代表是
    private int count;//收藏数量
    private int cid;//所属分类，必输
    private String rimage;//缩略图
    private int sid;//所属商家
    private String sourceId;//抓取数据的来源id

    private Category category;//所属分类
    private Seller seller;//所属商家
    private List<RouteImg> routeImgList;//商品详情图片列表



    */
/**
     * 无参构造方法
     *//*

    public Route(){}

    */
/**
     * 有参构造方法
     * @param rid
     * @param rname
     * @param price
     * @param routeIntroduce
     * @param rflag
     * @param rdate
     * @param isThemeTour
     * @param count
     * @param cid
     * @param rimage
     * @param sid
     * @param sourceId
     *//*

    public Route(int rid, String rname, double price, String routeIntroduce, String rflag, String rdate, String isThemeTour, int count, int cid, String rimage, int sid, String sourceId) {
        this.rid = rid;
        this.rname = rname;
        this.price = price;
        this.routeIntroduce = routeIntroduce;
        this.rflag = rflag;
        this.rdate = rdate;
        this.isThemeTour = isThemeTour;
        this.count = count;
        this.cid = cid;
        this.rimage = rimage;
        this.sid = sid;
        this.sourceId = sourceId;
    }

    public List<RouteImg> getRouteImgList() {
        return routeImgList;
    }

    public void setRouteImgList(List<RouteImg> routeImgList) {
        this.routeImgList = routeImgList;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getRouteIntroduce() {
        return routeIntroduce;
    }

    public void setRouteIntroduce(String routeIntroduce) {
        this.routeIntroduce = routeIntroduce;
    }

    public String getRflag() {
        return rflag;
    }

    public void setRflag(String rflag) {
        this.rflag = rflag;
    }

    public String getRdate() {
        return rdate;
    }

    public void setRdate(String rdate) {
        this.rdate = rdate;
    }

    public String getIsThemeTour() {
        return isThemeTour;
    }

    public void setIsThemeTour(String isThemeTour) {
        this.isThemeTour = isThemeTour;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getRimage() {
        return rimage;
    }

    public void setRimage(String rimage) {
        this.rimage = rimage;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }
}
package cn.dongruan.travel.domain;

        import java.io.Serializable;

*/
/**
 * 旅游线路图片实体类
 *//*

public class RouteImg implements Serializable {
    private int rgid;//商品图片id
    private int rid;//旅游商品id
    private String bigPic;//详情商品大图
    private String smallPic;//详情商品小图

    */
/**
     * 无参构造方法
     *//*

    public RouteImg() {
    }

    */
/**
     * 有参构造方法
     * @param rgid
     * @param rid
     * @param bigPic
     * @param smallPic
     *//*

    public RouteImg(int rgid, int rid, String bigPic, String smallPic) {
        this.rgid = rgid;
        this.rid = rid;
        this.bigPic = bigPic;
        this.smallPic = smallPic;
    }

    public int getRgid() {
        return rgid;
    }

    public void setRgid(int rgid) {
        this.rgid = rgid;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getBigPic() {
        return bigPic;
    }

    public void setBigPic(String bigPic) {
        this.bigPic = bigPic;
    }

    public String getSmallPic() {
        return smallPic;
    }

    public void setSmallPic(String smallPic) {
        this.smallPic = smallPic;
    }
}
package cn.dongruan.travel.domain;

        import java.io.Serializable;

*/
/**
 * 商家实体类
 *//*

public class Seller implements Serializable {
    private int sid;//商家id
    private String sname;//商家名称
    private String consphone;//商家电话
    private String address;//商家地址

    */
/**
     * 无参构造方法
     *//*

    public Seller(){}

    */
/**
     * 构造方法
     * @param sid
     * @param sname
     * @param consphone
     * @param address
     *//*

    public Seller(int sid, String sname, String consphone, String address) {
        this.sid = sid;
        this.sname = sname;
        this.consphone = consphone;
        this.address = address;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getConsphone() {
        return consphone;
    }

    public void setConsphone(String consphone) {
        this.consphone = consphone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
package cn.dongruan.travel.domain;

        import java.io.Serializable;

*/
/**
 * 用户实体类
 *//*

public class User implements Serializable {
    private int uid;//用户id
    private String username;//用户名，账号
    private String password;//密码
    private String name;//真实姓名
    private String birthday;//出生日期
    private String sex;//男或女
    private String telephone;//手机号
    private String email;//邮箱
    private String status;//激活状态，Y代表激活，N代表未激活
    private String code;//激活码（要求唯一）

    */
/**
     * 无参构造方法
     *//*

    public User() {
    }

    */
/**
     * 有参构方法
     * @param uid
     * @param username
     * @param password
     * @param name
     * @param birthday
     * @param sex
     * @param telephone
     * @param email
     * @param status
     * @param code
     *//*

    public User(int uid, String username, String password, String name, String birthday, String sex, String telephone, String email, String status, String code) {
        this.uid = uid;
        this.username = username;
        this.password = password;
        this.name = name;
        this.birthday = birthday;
        this.sex = sex;
        this.telephone = telephone;
        this.email = email;
        this.status = status;
        this.code = code;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
package cn.dongruan.travel.web.filter;

        import javax.servlet.*;
        import javax.servlet.annotation.WebFilter;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import java.io.IOException;

*/
/**
 * 解决全站乱码问题，处理所有的请求
 *//*

@WebFilter("/*")
public class CharchaterFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse rep, FilterChain filterChain) throws IOException, ServletException {
        //将父接口转为子接口
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) rep;
        //获取请求方法
        String method = request.getMethod();
        //解决post请求中文数据乱码问题
        if(method.equalsIgnoreCase("post")){
            request.setCharacterEncoding("utf-8");
        }
        //处理响应乱码
        response.setContentType("text/html;charset=utf-8");
        filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
package cn.dongruan.travel.web.servlet;

        import com.fasterxml.jackson.core.JsonProcessingException;
        import com.fasterxml.jackson.databind.ObjectMapper;

        import javax.servlet.ServletException;
        import javax.servlet.annotation.WebServlet;
        import javax.servlet.http.HttpServlet;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import java.io.IOException;
        import java.lang.reflect.InvocationTargetException;
        import java.lang.reflect.Method;

*/
/**
 * @Author: 东软
 * @Date: 2020-11-15
 *//*

public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        System.out.println(uri);
        String methodName = uri.substring(uri.lastIndexOf("/") + 1);
        System.out.println(methodName);
        System.out.println(this);
        try {
            Method method = this.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            try {
                method.invoke(this,req,resp);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public void writeValue(Object object,HttpServletResponse response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(), object);

    }

    public String writeValueAsString(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }
}
package cn.dongruan.travel.web.servlet;

        import cn.dongruan.travel.domain.Category;
        import cn.dongruan.travel.service.impl.CategoryService;
        import cn.dongruan.travel.service.impl.CategoryServiceImpl;
        import com.fasterxml.jackson.databind.ObjectMapper;

        import javax.servlet.ServletException;
        import javax.servlet.annotation.WebServlet;
        import javax.servlet.http.HttpServlet;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import java.io.IOException;
        import java.util.List;

*/
/**
 * @Author: 东软
 * @Date: 2020-11-15
 *//*

@WebServlet("/category/*")
public class CategoryServlet extends BaseServlet {
    private CategoryService service = new CategoryServiceImpl();

    public void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> cs = service.findAll();
        writeValue(cs, response);
    }


}
package cn.dongruan.travel.web.servlet;

        import cn.dongruan.travel.domain.PageBean;
        import cn.dongruan.travel.domain.Route;
        import cn.dongruan.travel.domain.User;
        import cn.dongruan.travel.service.impl.FavoriteService;
        import cn.dongruan.travel.service.impl.FavoriteServiceImpl;
        import cn.dongruan.travel.service.impl.RouteService;
        import cn.dongruan.travel.service.impl.RouteServiceImpl;

        import javax.servlet.ServletException;
        import javax.servlet.annotation.WebServlet;
        import javax.servlet.http.HttpServlet;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import java.io.IOException;

*/
/**
 * @Author: 东软
 * @Date: 2020-11-16
 *//*

@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {

    private RouteService routeService = new RouteServiceImpl();
    private FavoriteService favoriteService = new FavoriteServiceImpl();

    */
/**
     * 分页查询
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     *//*


    public void pageQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        String cidStr = request.getParameter("cid");

        //接受rname 线路名称
        String rname = request.getParameter("rname");
        rname = new String(rname.getBytes("iso-8859-1"), "utf-8");


        int cid = 0;//类别id
        //2.处理参数
        if (cidStr != null && cidStr.length() > 0 && !"null".equals(cidStr)) {
            cid = Integer.parseInt(cidStr);
        }
        int currentPage = 0;//当前页码，如果不传递，则默认为第一页
        if (currentPageStr != null && currentPageStr.length() > 0) {
            currentPage = Integer.parseInt(currentPageStr);
        } else {
            currentPage = 1;
        }

        int pageSize = 0;//每页显示条数，如果不传递，默认每页显示5条记录
        if (pageSizeStr != null && pageSizeStr.length() > 0) {
            pageSize = Integer.parseInt(pageSizeStr);
        } else {
            pageSize = 5;
        }

        //3. 调用service查询PageBean对象
        PageBean<Route> pb = routeService.pageQuery(cid, currentPage, pageSize, rname);

        //4. 将pageBean对象序列化为json，返回
        writeValue(pb, response);
    }

    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rid = request.getParameter("rid");
        Route route = routeService.findOne(rid);
        writeValue(route, response);
    }
    public void isFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rid = request.getParameter("rid");
        // Route route = routeService.findOne(rid);
        User user = (User) request.getSession().getAttribute("user");
        int uid;
        if (user == null) {
            uid = 0;
        } else {

            uid = user.getUid();
        }
        boolean flag = favoriteService.isFavorite(rid, uid);
        writeValue(flag, response);

    }
    public void addFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rid = request.getParameter("rid");
        User user = (User) request.getSession().getAttribute("user");
        int uid;
        if (user == null) {
            return;
        } else {
            uid = user.getUid();
        }
        favoriteService.add(rid,uid);
    }
}
package cn.dongruan.travel.web.servlet;

        import cn.dongruan.travel.domain.ResultInfo;
        import cn.dongruan.travel.domain.User;
        import cn.dongruan.travel.service.impl.UserService;
        import cn.dongruan.travel.service.impl.UserServiceImpl;
        import com.fasterxml.jackson.databind.ObjectMapper;
        import org.apache.commons.beanutils.BeanUtils;

        import javax.imageio.ImageIO;
        import javax.servlet.ServletException;
        import javax.servlet.annotation.WebServlet;
        import javax.servlet.http.HttpServlet;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import javax.servlet.http.HttpSession;
        import java.awt.*;
        import java.awt.image.BufferedImage;
        import java.io.IOException;
        import java.lang.reflect.InvocationTargetException;
        import java.util.Map;
        import java.util.Random;

*/
/**
 * @Author: 东软
 * @Date: 2020-11-15
 *//*

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    private UserService service = new UserServiceImpl();

    public void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String check = request.getParameter("check");
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");
        if (checkcode_server==null||!checkcode_server.equalsIgnoreCase(check)) {
            ResultInfo info = new ResultInfo();
            info.setFlag(false);
            info.setErrorMsg("验证码错误");
            */
/*ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(info);*//*

            String json = writeValueAsString(info);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(json);
            return;
        }
        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //UserService service = new UserServiceImpl();
        boolean flag = service.regist(user);
        ResultInfo info = new ResultInfo();
        if (flag) {
            info.setFlag(true);

        } else {
            info.setErrorMsg("注册失败");

        }
        */
/*ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(info);*//*

        String json = writeValueAsString(info);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }

    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //UserService service = new UserServiceImpl();
        User u = service.login(user);
        ResultInfo info = new ResultInfo();
        if (u == null) {
            info.setFlag(false);
            info.setErrorMsg("用户名或密码错误");

        }
        if (u != null && !"Y".equals(u.getStatus())) {
            info.setFlag(false);
            info.setErrorMsg("您尚未激活，请激活");

        }
        if (u != null && "Y".equals(u.getStatus())) {
            request.getSession().setAttribute("user",u);//登录成功标记
            info.setFlag(true);

        }
        */
/*ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(), info);*//*

        writeValue(info, response);
    }
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从session中获取登录用户
        Object user = request.getSession().getAttribute("user");
        //将user写回客户端

        */
/*ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),user);*//*

        writeValue(user, response);
    }
    public void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath()+"/login.html");
    }
    public void active(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        if (code != null) {
            //UserService ervice = new UserServiceImpl();
            boolean flag = service.active(code);
            String msg = null;
            if(flag){
                //激活成功
                msg = "激活成功，请<a href='login.html'>登录</a>";
            }else{
                //激活失败
                msg = "激活失败，请联系管理员!";
            }
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(msg);
        }
    }
    public void checkCode(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {

        //服务器通知浏览器不要缓存
        response.setHeader("pragma","no-cache");
        response.setHeader("cache-control","no-cache");
        response.setHeader("expires","0");

        //在内存中创建一个长80，宽30的图片，默认黑色背景
        //参数一：长
        //参数二：宽
        //参数三：颜色
        int width = 80;
        int height = 30;
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);

        //获取画笔
        Graphics g = image.getGraphics();
        //设置画笔颜色为灰色
        g.setColor(Color.GRAY);
        //填充图片
        g.fillRect(0,0, width,height);

        //产生4个随机验证码，12Ey
        String checkCode = getCheckCode();
        //将验证码放入HttpSession中
        request.getSession().setAttribute("CHECKCODE_SERVER",checkCode);

        //设置画笔颜色为黄色
        g.setColor(Color.YELLOW);
        //设置字体的小大
        g.setFont(new Font("黑体",Font.BOLD,24));
        //向图片上写入验证码
        g.drawString(checkCode,15,25);

        //将内存中的图片输出到浏览器
        //参数一：图片对象
        //参数二：图片的格式，如PNG,JPG,GIF
        //参数三：图片输出到哪里去
        ImageIO.write(image,"PNG",response.getOutputStream());
    }
    */
/**
     * 产生4位随机字符串
     *//*

    private String getCheckCode() {
        String base = "0123456789ABCDEFGabcdefg";
        int size = base.length();
        Random r = new Random();
        StringBuffer sb = new StringBuffer();
        for(int i=1;i<=4;i++){
            //产生0到size-1的随机值
            int index = r.nextInt(size);
            //在base字符串中获取下标为index的字符
            char c = base.charAt(index);
            //将c放入到StringBuffer中去
            sb.append(c);
        }
        return sb.toString();
    }
}
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>东软旅游网-登录</title>
<link rel="stylesheet" type="text/css" href="css/common.css">
<link rel="stylesheet" type="text/css" href="css/login.css">
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
<script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
<script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
<!--导入angularJS文件-->
<!--    <script src="js/angular.min.js"></script>-->
<!--导入jquery-->
<script src="js/jquery-3.3.1.js"></script>
<script>
      $(function () {
              $("#btn_sub").click(function () {
              $.post("user/login", $("#loginForm").serialize(), function (data) {
              if (data.flag) {
              location.href = "index.html";
              } else {
              $("#errorMsg").html(data.errorMsg);
              }

              });


              });

              });
</script>
</head>

<body>
<!--引入头部-->
<div id="header"></div>
<!-- 头部 end -->
<section id="login_wrap">
<div class="fullscreen-bg" style="background: url(images/login_bg.png);height: 532px;">

</div>
<div class="login-box">
<div class="title">
<img src="images/login_logo.png" alt="">
<span>欢迎登录东软旅游账户</span>
</div>
<div class="login_inner">

<!--登录错误提示消息-->
<div id="errorMsg" class="alert alert-danger" ></div>
<form id="loginForm" action="" method="post" accept-charset="utf-8">
<input type="hidden" name="action" value="login"/>
<input name="username" type="text" placeholder="请输入账号" autocomplete="off">
<input name="password" type="text" placeholder="请输入密码" autocomplete="off">
<div class="verify">
<input name="check" type="text" placeholder="请输入验证码" autocomplete="off">
<span><img src="checkCode" alt="" onclick="changeCheckCode(this)"></span>
<script type="text/javascript">
        //图片点击事件
        function changeCheckCode(img) {
        img.src="user/checkCode?"+new Date().getTime();
        }
</script>
</div>
<div class="submit_btn">
<button type="button" id="btn_sub">登录</button>

<div class="auto_login">
<input type="checkbox" name="" class="checkbox">
<span>自动登录</span>
</div>
</div>
</form>
<div class="reg">没有账户？<a href="javascript:;">立即注册</a></div>
</div>
</div>
</section>
<!--引入尾部-->
<div id="footer"></div>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="js/jquery-1.11.0.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>
<!--导入布局js，共享header和footer-->
<script type="text/javascript" src="js/include.js"></script>
</body>

</html>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>东软旅游网-我的收藏</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/common.css">
<link rel="stylesheet" href="css/index.css">
<style>
           .tab-content .row>div {
                   margin-top: 16px;
                   }
                   .tab-content {
                   margin-bottom: 36px;
                   }
</style>
<script src="js/jquery-3.3.1.js"></script>
</head>
<body>
<!--引入头部-->
<div id="header"></div>
<!-- 排行榜 start-->
<section id="content">
<section class="hemai_jx">
<div class="jx_top">
<div class="jx_tit">
<img src="images/icon_5.jpg" alt="">
<span>我的收藏</span>
</div>
</div>
<div class="jx_content">
<!-- Tab panes -->
<div class="tab-content">
<div role="tabpanel" class="tab-pane active" id="home">
<div class="row">
<div class="col-md-3">
<a href="route_detail.html">
<img src="images/collection_pic.jpg" alt="">
<div class="has_border">
<h3>上海直飞三亚5天4晚自由行(春节预售+亲子/蜜月/休闲游首选+豪华酒店任选+接送机)</h3>
<div class="price">网付价<em>￥</em><strong>889</strong><em>起</em></div>
</div>
</a>
</div>
<div class="col-md-3">
<a href="route_detail.html">
<img src="images/collection_pic.jpg" alt="">
<div class="has_border">
<h3>上海直飞三亚5天4晚自由行(春节预售+亲子/蜜月/休闲游首选+豪华酒店任选+接送机)</h3>
<div class="price">网付价<em>￥</em><strong>889</strong><em>起</em></div>
</div>
</a>
</div>
<div class="col-md-3">
<a href="route_detail.html">
<img src="images/collection_pic.jpg" alt="">
<div class="has_border">
<h3>上海直飞三亚5天4晚自由行(春节预售+亲子/蜜月/休闲游首选+豪华酒店任选+接送机)</h3>
<div class="price">网付价<em>￥</em><strong>889</strong><em>起</em></div>
</div>
</a>
</div>
<div class="col-md-3">
<a href="route_detail.html">
<img src="images/collection_pic.jpg" alt="">
<div class="has_border">
<h3>上海直飞三亚5天4晚自由行(春节预售+亲子/蜜月/休闲游首选+豪华酒店任选+接送机)</h3>
<div class="price">网付价<em>￥</em><strong>889</strong><em>起</em></div>
</div>
</a>
</div>
<div class="col-md-3">
<a href="route_detail.html">
<img src="images/collection_pic.jpg" alt="">
<div class="has_border">
<h3>上海直飞三亚5天4晚自由行(春节预售+亲子/蜜月/休闲游首选+豪华酒店任选+接送机)</h3>
<div class="price">网付价<em>￥</em><strong>889</strong><em>起</em></div>
</div>
</a>
</div>
<div class="col-md-3">
<a href="route_detail.html">
<img src="images/collection_pic.jpg" alt="">
<div class="has_border">
<h3>上海直飞三亚5天4晚自由行(春节预售+亲子/蜜月/休闲游首选+豪华酒店任选+接送机)</h3>
<div class="price">网付价<em>￥</em><strong>889</strong><em>起</em></div>
</div>
</a>
</div>
<div class="col-md-3">
<a href="route_detail.html">
<img src="images/collection_pic.jpg" alt="">
<div class="has_border">
<h3>上海直飞三亚5天4晚自由行(春节预售+亲子/蜜月/休闲游首选+豪华酒店任选+接送机)</h3>
<div class="price">网付价<em>￥</em><strong>889</strong><em>起</em></div>
</div>
</a>
</div>
<div class="col-md-3">
<a href="route_detail.html">
<img src="images/collection_pic.jpg" alt="">
<div class="has_border">
<h3>上海直飞三亚5天4晚自由行(春节预售+亲子/蜜月/休闲游首选+豪华酒店任选+接送机)</h3>
<div class="price">网付价<em>￥</em><strong>889</strong><em>起</em></div>
</div>
</a>
</div>
<div class="col-md-3">
<a href="route_detail.html">
<img src="images/collection_pic.jpg" alt="">
<div class="has_border">
<h3>上海直飞三亚5天4晚自由行(春节预售+亲子/蜜月/休闲游首选+豪华酒店任选+接送机)</h3>
<div class="price">网付价<em>￥</em><strong>889</strong><em>起</em></div>
</div>
</a>
</div>
<div class="col-md-3">
<a href="route_detail.html">
<img src="images/collection_pic.jpg" alt="">
<div class="has_border">
<h3>上海直飞三亚5天4晚自由行(春节预售+亲子/蜜月/休闲游首选+豪华酒店任选+接送机)</h3>
<div class="price">网付价<em>￥</em><strong>889</strong><em>起</em></div>
</div>
</a>
</div>
<div class="col-md-3">
<a href="route_detail.html">
<img src="images/collection_pic.jpg" alt="">
<div class="has_border">
<h3>上海直飞三亚5天4晚自由行(春节预售+亲子/蜜月/休闲游首选+豪华酒店任选+接送机)</h3>
<div class="price">网付价<em>￥</em><strong>889</strong><em>起</em></div>
</div>
</a>
</div>
<div class="col-md-3">
<a href="jroute_detail.html">
<img src="images/collection_pic.jpg" alt="">
<div class="has_border">
<h3>上海直飞三亚5天4晚自由行(春节预售+亲子/蜜月/休闲游首选+豪华酒店任选+接送机)</h3>
<div class="price">网付价<em>￥</em><strong>889</strong><em>起</em></div>
</div>
</a>
</div>
</div>
</div>
</div>
</div>
<div class="pageNum">
<ul>
<li><a href="">首页</a></li>
<li class="threeword"><a href="#">上一页</a></li>
<li><a href="#">1</a></li>
<li><a href="#">2</a></li>
<li><a href="#">3</a></li>
<li><a href="#">4</a></li>
<li><a href="#">5</a></li>
<li><a href="#">6</a></li>
<li><a href="#">7</a></li>
<li><a href="#">8</a></li>
<li><a href="#">9</a></li>
<li><a href="#">10</a></li>
<li class="threeword"><a href="javascript:;">下一页</a></li>
<li class="threeword"><a href="javascript:;">末页</a></li>
</ul>
</div>
</section>
</section>
<!-- 排行榜 end-->

<!--引入尾部-->
<div id="footer"></div>
<!--导入布局js，共享header和footer-->
<script type="text/javascript" src="js/include.js"></script>
</body>
</html>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>注册</title>
<link rel="stylesheet" type="text/css" href="css/common.css">
<link rel="stylesheet" href="css/register.css">
<!--导入jquery-->
<script src="js/jquery-3.3.1.js"></script>
<script>
         function checkUsername() {
                 //1.获取用户名值
                 var username = $("#username").val();
                 //2.定义正则
                 var reg_username = /^\w{8,20}$/;

                 //3.判断，给出提示信息
                 var flag = reg_username.test(username);
                 if(flag){
                 //用户名合法
                 $("#username").css("border","");
                 }else{
                 //用户名非法,加一个红色边框
                 $("#username").css("border","1px solid red");
                 }

                 return flag;
                 }

                 //校验密码
                 function checkPassword() {
                 //1.获取密码值
                 var password = $("#password").val();
                 //2.定义正则
                 var reg_password = /^\w{8,20}$/;

                 //3.判断，给出提示信息
                 var flag = reg_password.test(password);
                 if(flag){
                 //密码合法
                 $("#password").css("border","");
                 }else{
                 //密码非法,加一个红色边框
                 $("#password").css("border","1px solid red");
                 }

                 return flag;
                 }

                 //校验邮箱
                 function checkEmail(){
                 //1.获取邮箱
                 var email = $("#email").val();
                 //2.定义正则      dongruan@163.com
                 var reg_email = /^\w+@\w+\.\w+$/;

        //3.判断
        var flag = reg_email.test(email);
        if(flag){
        $("#email").css("border","");
        }else{
        $("#email").css("border","1px solid red");
        }

        return flag;
        }

        $(function () {
        //当表单提交时，调用所有的校验方法
        $("#registerForm").submit(function(){
        //1.发送数据到服务器
        if(checkUsername() && checkPassword() && checkEmail()){
        //校验通过,发送ajax请求，提交表单的数据   username=zhangsan&password=123

        $.post("user/regist", $(this).serialize(), function (data) {
        //处理服务器响应的数据  data  {flag:true,errorMsg:"注册失败"}
        if (data.flag == true) {
        location.href = "register_ok.html";
        }else {
        $("#errorMsg").html(data.errorMsg);
        }
        });
        }
        //2.不让页面跳转
        return false;
        //如果这个方法没有返回值，或者返回为true，则表单提交，如果返回为false，则表单不提交
        });
        //当某一个组件失去焦点是，调用对应的校验方法
        $("#username").blur(checkUsername);
        $("#password").blur(checkPassword);
        $("#email").blur(checkEmail);
        });
</script>
</head>
<body>
<!--引入头部-->
<div id="header"></div>
<!-- 头部 end -->
<div class="rg_layout">
<div class="rg_form clearfix">
<div class="rg_form_left">
<p>新用户注册</p>
<p>USER REGISTER</p>
</div>
<div class="rg_form_center">
<div id="errorMsg" style="color: red;text-align: center"></div>

<!--注册表单-->
<form id="registerForm" action="user">
<!--提交处理请求的标识符-->
<input type="hidden" name="action" value="register">
<table style="margin-top: 25px;">
<tr>
<td class="td_left">
<label for="username">用户名</label>
</td>
<td class="td_right">
<input type="text" id="username" name="username" placeholder="请输入账号">
</td>
</tr>
<tr>
<td class="td_left">
<label for="password">密码</label>
</td>
<td class="td_right">
<input type="text" id="password" name="password" placeholder="请输入密码">
</td>
</tr>
<tr>
<td class="td_left">
<label for="email">Email</label>
</td>
<td class="td_right">
<input type="text" id="email" name="email" placeholder="请输入Email">
</td>
</tr>
<tr>
<td class="td_left">
<label for="name">姓名</label>
</td>
<td class="td_right">
<input type="text" id="name" name="name" placeholder="请输入真实姓名">
</td>
</tr>
<tr>
<td class="td_left">
<label for="telephone">手机号</label>
</td>
<td class="td_right">
<input type="text" id="telephone" name="telephone" placeholder="请输入您的手机号">
</td>
</tr>
<tr>
<td class="td_left">
<label for="sex">性别</label>
</td>
<td class="td_right gender">
<input type="radio" id="sex" name="sex" value="男" checked> 男
<input type="radio" name="sex" value="女"> 女
</td>
</tr>
<tr>
<td class="td_left">
<label for="birthday">出生日期</label>
</td>
<td class="td_right">
<input type="date" id="birthday" name="birthday" placeholder="年/月/日">
</td>
</tr>
<tr>
<td class="td_left">
<label for="check">验证码</label>
</td>
<td class="td_right check">
<input type="text" id="check" name="check" class="check">
<img src="checkCode" height="32px" alt="" onclick="changeCheckCode(this)">
<script type="text/javascript">
        //图片点击事件
        function changeCheckCode(img) {
        img.src="user/checkCode?"+new Date().getTime();
        }
</script>
</td>
</tr>
<tr>
<td class="td_left">
</td>
<td class="td_right check">
<input type="submit" class="submit" value="注册">
<span id="msg" style="color: red;"></span>
</td>
</tr>
</table>
</form>
</div>
<div class="rg_form_right">
<p>
                   已有账号？
<a href="#">立即登录</a>
</p>
</div>
</div>
</div>
<!--引入尾部-->
<div id="footer"></div>
<!--导入布局js，共享header和footer-->
<script type="text/javascript" src="js/include.js"></script>

</body>
</html>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>注册</title>
<link rel="stylesheet" type="text/css" href="css/common.css">
<link rel="stylesheet" href="css/register.css">
<!--导入jquery-->
<script src="js/jquery-3.3.1.js"></script>
</head>
<body>
<!--引入头部-->
<div id="header"></div>
<!-- 头部 end -->
<div style="text-align:center;red:yellow;font-weight:bold;height:150px;padding-top:100px;font-size:30px;">
<h4>恭喜，注册成功！请登录您的注册邮箱进行激活您的账号，激活后才能登录。</h4>
</div>
<!--引入尾部-->
<div id="footer"></div>
<!--导入布局js，共享header和footer-->
<script type="text/javascript" src="js/include.js"></script>
</body>
</html>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<title>路线详情</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/common.css">
<link rel="stylesheet" type="text/css" href="css/route-detail.css">



</head>

<body>
<!--引入头部-->
<div id="header"></div>
<!-- 详情 start -->
<div class="wrap">
<div class="bread_box">
<a href="/">首页</a>
<span> &gt;</span>
<a href="#">国内游</a><span> &gt;</span>
<a href="#">全国-曼谷6-7天自由行 泰国出境旅游 特价往返机票自由行二次确认</a>
</div>
<div class="prosum_box">
<dl class="prosum_left">
<dt>
<img alt="" class="big_img" src="http://www.jinmalvyou.com/Public/uploads/goods_img/img_size4/201703/m49788843d72171643297ccc033d9288ee.jpg">
</dt>
<dd id="dd">
<a class="up_img up_img_disable"></a>
<a title="" class="little_img" data-bigpic="http://www.jinmalvyou.com/Public/uploads/goods_img/img_size4/201703/m40920d0669855e745d97f9ad1df966ebb.jpg">
<img src="http://www.jinmalvyou.com/Public/uploads/goods_img/img_size2/201703/m20920d0669855e745d97f9ad1df966ebb.jpg">
</a>
<a title="" class="little_img cur_img" data-bigpic="http://www.jinmalvyou.com/Public/uploads/goods_img/img_size4/201703/m49788843d72171643297ccc033d9288ee.jpg">
<img src="http://www.jinmalvyou.com/Public/uploads/goods_img/img_size2/201703/m29788843d72171643297ccc033d9288ee.jpg">
</a>
<a title="" class="little_img" data-bigpic="http://www.jinmalvyou.com/Public/uploads/goods_img/img_size4/201703/m4531a8dbceefa2c44e6d0e35627cd2689.jpg">
<img src="http://www.jinmalvyou.com/Public/uploads/goods_img/img_size2/201703/m2531a8dbceefa2c44e6d0e35627cd2689.jpg">
</a>
<a title="" class="little_img" data-bigpic="http://www.jinmalvyou.com/Public/uploads/goods_img/img_size4/201703/m46d8cb900e9f6c0a762aca19eae40c00c.jpg">
<img src="http://www.jinmalvyou.com/Public/uploads/goods_img/img_size2/201703/m26d8cb900e9f6c0a762aca19eae40c00c.jpg">
</a>
<a title="" class="little_img" data-bigpic="http://www.jinmalvyou.com/Public/uploads/goods_img/img_size4/201703/m45ea00f6eba562a767b5095bbf8cffe07.jpg" style="display:none;">
<img src="http://www.jinmalvyou.com/Public/uploads/goods_img/img_size2/201703/m25ea00f6eba562a767b5095bbf8cffe07.jpg">
</a>
<a title="" class="little_img" data-bigpic="http://www.jinmalvyou.com/Public/uploads/goods_img/img_size4/201703/m4265ec488cd1bc7ce749bc8c9b34b87bc.jpg" style="display:none;">
<img src="http://www.jinmalvyou.com/Public/uploads/goods_img/img_size2/201703/m2265ec488cd1bc7ce749bc8c9b34b87bc.jpg">
</a>
<a title="" class="little_img" data-bigpic="http://www.jinmalvyou.com/Public/uploads/goods_img/img_size4/201703/m4e7e964909d7dd1a9f6e5494d4dc0c847.jpg" style="display:none;">
<img src="http://www.jinmalvyou.com/Public/uploads/goods_img/img_size2/201703/m2e7e964909d7dd1a9f6e5494d4dc0c847.jpg">
</a>
<a title="" class="little_img" data-bigpic="http://www.jinmalvyou.com/Public/uploads/goods_img/img_size4/201703/m467db00e1b76718fab0fe8b96e10f4d35.jpg" style="display:none;">
<img src="http://www.jinmalvyou.com/Public/uploads/goods_img/img_size2/201703/m267db00e1b76718fab0fe8b96e10f4d35.jpg">
</a>
<a title="" class="little_img" data-bigpic="http://www.jinmalvyou.com/Public/uploads/goods_img/img_size4/201703/m487bbbc6e43eba6aa6a36cc1a182f7a20.jpg" style="display:none;">
<img src="http://www.jinmalvyou.com/Public/uploads/goods_img/img_size2/201703/m287bbbc6e43eba6aa6a36cc1a182f7a20.jpg">
</a>
<a class="down_img down_img_disable" style="margin-bottom: 0;"></a>
</dd>
</dl>
<div class="prosum_right">
<p class="pros_title" id="rname">【尾单特卖】全国-曼谷6-7天自由行 泰国出境旅游 特价往返机票自由行二次确认</p>
<p class="hot" id="routeIntroduce">1-2月出发，网付立享￥1099/2人起！爆款位置有限，抢完即止！</p>
<div class="pros_other">
<p >经营商家  ：<span id="sname">东软国旅</span></p>
<p >咨询电话 : <span id="consphone">400-618-9090</span></p>
<p >地址 ： <span id="address">东软程序员</span></p>
</div>
<div class="pros_price">
<p class="price"><strong id="price">¥2699.00</strong><span>起</span></p>
<p class="collect">
<a class="btn" id="favorite" onclick="addFavorite();"><i class="glyphicon glyphicon-heart-empty"></i>点击收藏</a>

<!-- <a  class="btn already" disabled="disabled"><i class="glyphicon glyphicon-heart-empty"></i>点击收藏</a>-->
<span id="favoriteCount">已收藏100次</span>
</p>
</div>
</div>
</div>
<div class="you_need_konw">
<span>旅游须知</span>
<div class="notice">
<p>1、旅行社已投保旅行社责任险。建议游客购买旅游意外保险 <br>

<p>2、旅游者参加打猎、潜水、海边游泳、漂流、滑水、滑雪、滑草、蹦极、跳伞、滑翔、乘热气球、骑马、赛车、攀岩、水疗、水上飞机等属于高风险性游乐项目的，敬请旅游者务必在参加前充分了解项目的安全须知并确保身体状况能适应此类活动；如旅游者不具备较好的身体条件及技能，可能会造成身体伤害。</p>

<p>3、参加出海活动时，请务必穿着救生设备。参加水上活动应注意自己的身体状况，有心脏病、冠心病、高血压、感冒、发烧和饮酒及餐后不可以参加水上活动及潜水。在海里活动时，严禁触摸海洋中各种鱼类，水母，海胆，珊瑚等海洋生物，避免被其蛰伤。老人和小孩必须有成年人陪同才能参加合适的水上活动。在海边游玩时，注意保管好随身携带的贵重物品。</p>

<p>4、根据中国海关总署的规定，旅客在境外购买的物品，在进入中国海关时可能需要征收关税。详细内容见《中华人民共和国海关总署公告2010年第54号文件》。</p>

<p>5、建议出发时行李托运，贵重物品、常用物品、常用药品、御寒衣物等请随身携带，尽量不要托运。行李延误属于不可抗力因素，我司将全力协助客人跟进后续工作，但我司对此不承担任何责任。</p>
<p>1、旅行社已投保旅行社责任险。建议游客购买旅游意外保险 <br>

<p>2、旅游者参加打猎、潜水、海边游泳、漂流、滑水、滑雪、滑草、蹦极、跳伞、滑翔、乘热气球、骑马、赛车、攀岩、水疗、水上飞机等属于高风险性游乐项目的，敬请旅游者务必在参加前充分了解项目的安全须知并确保身体状况能适应此类活动；如旅游者不具备较好的身体条件及技能，可能会造成身体伤害。</p>

<p>3、参加出海活动时，请务必穿着救生设备。参加水上活动应注意自己的身体状况，有心脏病、冠心病、高血压、感冒、发烧和饮酒及餐后不可以参加水上活动及潜水。在海里活动时，严禁触摸海洋中各种鱼类，水母，海胆，珊瑚等海洋生物，避免被其蛰伤。老人和小孩必须有成年人陪同才能参加合适的水上活动。在海边游玩时，注意保管好随身携带的贵重物品。</p>

<p>4、根据中国海关总署的规定，旅客在境外购买的物品，在进入中国海关时可能需要征收关税。详细内容见《中华人民共和国海关总署公告2010年第54号文件》。</p>

<p>5、建议出发时行李托运，贵重物品、常用物品、常用药品、御寒衣物等请随身携带，尽量不要托运。行李延误属于不可抗力因素，我司将全力协助客人跟进后续工作，但我司对此不承担任何责任。</p>
</div>
</div>
</div>
<!-- 详情 end -->

<!--引入头部-->
<div id="footer"></div>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="js/jquery-3.3.1.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>
<!--导入布局js，共享header和footer-->
<script type="text/javascript" src="js/include.js"></script>
<script src="js/getParameter.js"></script>
<script>
    $(document).ready(function() {

            //自动播放
            goImg();
            // var timer = setInterval("auto_play()", 5000);
            });
            function goImg(){
            //焦点图效果
            //点击图片切换图片
            $('.little_img').on('mousemove', function() {
            $('.little_img').removeClass('cur_img');
            var big_pic = $(this).data('bigpic');
            $('.big_img').attr('src', big_pic);
            $(this).addClass('cur_img');
            });
            //上下切换
            var picindex = 0;
            var nextindex = 4;
            $('.down_img').on('click',function(){
            var num = $('.little_img').length;
            if((nextindex + 1) <= num){
            $('.little_img:eq('+picindex+')').hide();
            $('.little_img:eq('+nextindex+')').show();
            picindex = picindex + 1;
            nextindex = nextindex + 1;
            }
            });
            $('.up_img').on('click',function(){
            var num = $('.little_img').length;
            if(picindex > 0){
            $('.little_img:eq('+(nextindex-1)+')').hide();
            $('.little_img:eq('+(picindex-1)+')').show();
            picindex = picindex - 1;
            nextindex = nextindex - 1;
            }
            });
            }
            //自动轮播方法
            function auto_play() {
            var cur_index = $('.prosum_left dd').find('a.cur_img').index();
            cur_index = cur_index - 1;
            var num = $('.little_img').length;
            var max_index = 3;
            if ((num - 1) < 3) {
        max_index = num - 1;
        }
        if (cur_index < max_index) {
        var next_index = cur_index + 1;
        var big_pic = $('.little_img:eq(' + next_index + ')').data('bigpic');
        $('.little_img').removeClass('cur_img');
        $('.little_img:eq(' + next_index + ')').addClass('cur_img');
        $('.big_img').attr('src', big_pic);
        } else {
        var big_pic = $('.little_img:eq(0)').data('bigpic');
        $('.little_img').removeClass('cur_img');
        $('.little_img:eq(0)').addClass('cur_img');
        $('.big_img').attr('src', big_pic);
        }
        }


        $(function () {

        */
/*

                 <dd>
                    <a class="up_img up_img_disable"></a>
                    <a title="" class="little_img" data-bigpic="http://www.jinmalvyou.com/Public/uploads/goods_img/img_size4/201703/m40920d0669855e745d97f9ad1df966ebb.jpg">
                        <img src="http://www.jinmalvyou.com/Public/uploads/goods_img/img_size2/201703/m20920d0669855e745d97f9ad1df966ebb.jpg">
                    </a>
                    <a title="" class="little_img cur_img" data-bigpic="http://www.jinmalvyou.com/Public/uploads/goods_img/img_size4/201703/m49788843d72171643297ccc033d9288ee.jpg">
                        <img src="http://www.jinmalvyou.com/Public/uploads/goods_img/img_size2/201703/m29788843d72171643297ccc033d9288ee.jpg">
                    </a>
                    <a title="" class="little_img" data-bigpic="http://www.jinmalvyou.com/Public/uploads/goods_img/img_size4/201703/m4531a8dbceefa2c44e6d0e35627cd2689.jpg">
                        <img src="http://www.jinmalvyou.com/Public/uploads/goods_img/img_size2/201703/m2531a8dbceefa2c44e6d0e35627cd2689.jpg">
                    </a>
                    <a title="" class="little_img" data-bigpic="http://www.jinmalvyou.com/Public/uploads/goods_img/img_size4/201703/m46d8cb900e9f6c0a762aca19eae40c00c.jpg">
                        <img src="http://www.jinmalvyou.com/Public/uploads/goods_img/img_size2/201703/m26d8cb900e9f6c0a762aca19eae40c00c.jpg">
                    </a>
                    <a title="" class="little_img" data-bigpic="http://www.jinmalvyou.com/Public/uploads/goods_img/img_size4/201703/m45ea00f6eba562a767b5095bbf8cffe07.jpg" style="display:none;">
                        <img src="http://www.jinmalvyou.com/Public/uploads/goods_img/img_size2/201703/m25ea00f6eba562a767b5095bbf8cffe07.jpg">
                    </a>
                    <a title="" class="little_img" data-bigpic="http://www.jinmalvyou.com/Public/uploads/goods_img/img_size4/201703/m4265ec488cd1bc7ce749bc8c9b34b87bc.jpg" style="display:none;">
                        <img src="http://www.jinmalvyou.com/Public/uploads/goods_img/img_size2/201703/m2265ec488cd1bc7ce749bc8c9b34b87bc.jpg">
                    </a>
                    <a title="" class="little_img" data-bigpic="http://www.jinmalvyou.com/Public/uploads/goods_img/img_size4/201703/m4e7e964909d7dd1a9f6e5494d4dc0c847.jpg" style="display:none;">
                        <img src="http://www.jinmalvyou.com/Public/uploads/goods_img/img_size2/201703/m2e7e964909d7dd1a9f6e5494d4dc0c847.jpg">
                    </a>
                    <a title="" class="little_img" data-bigpic="http://www.jinmalvyou.com/Public/uploads/goods_img/img_size4/201703/m467db00e1b76718fab0fe8b96e10f4d35.jpg" style="display:none;">
                        <img src="http://www.jinmalvyou.com/Public/uploads/goods_img/img_size2/201703/m267db00e1b76718fab0fe8b96e10f4d35.jpg">
                    </a>
                    <a title="" class="little_img" data-bigpic="http://www.jinmalvyou.com/Public/uploads/goods_img/img_size4/201703/m487bbbc6e43eba6aa6a36cc1a182f7a20.jpg" style="display:none;">
                        <img src="http://www.jinmalvyou.com/Public/uploads/goods_img/img_size2/201703/m287bbbc6e43eba6aa6a36cc1a182f7a20.jpg">
                    </a>
                    <a class="down_img down_img_disable" style="margin-bottom: 0;"></a>
                </dd>


         *//*


        //1.获取rid
        var rid = getParameter("rid");

        //2.发送请求请求 route/findOne
        $.get("route/findOne",{rid:rid},function (route) {
        //3.解析数据填充html
        $("#rname").html(route.rname);
        $("#routeIntroduce").html(route.routeIntroduce);
        $("#price").html("¥"+route.price);
        $("#sname").html(route.seller.sname);
        $("#consphone").html(route.seller.consphone);
        $("#address").html(route.seller.address);
        //设置收藏次数
        $("#favoriteCount").html("已收藏"+route.count+"次");


        //图片展示

        var ddstr = '<a class="up_img up_img_disable"></a>';

        //遍历routeImgList
        for (var i = 0; i < route.routeImgList.length; i++) {
        var astr ;
        if(i >= 4){
        astr = '<a title="" class="little_img" data-bigpic="'+route.routeImgList[i].bigPic+'" style="display:none;">\n' +
        '                        <img src="'+route.routeImgList[i].smallPic+'">\n' +
        '                    </a>';
        }else{
        astr = '<a title="" class="little_img" data-bigpic="'+route.routeImgList[i].bigPic+'">\n' +
        '                        <img src="'+route.routeImgList[i].smallPic+'">\n' +
        '                    </a>';
        }


        ddstr += astr;
        }
        ddstr+='<a class="down_img down_img_disable" style="margin-bottom: 0;"></a>';

        $("#dd").html(ddstr);

        //图片展示和切换代码调用
        goImg();
        });
        });


        $(function () {
        // 发送请求，判断用户是否收藏过该线路
        var rid = getParameter("rid");
        $.get("route/isFavorite",{rid:rid},function (flag) {
        if(flag){
        // 用户已经收藏过
        //<a  class="btn already" disabled="disabled">
        //设置收藏按钮的样式
        $("#favorite").addClass("already");
        $("#favorite").attr("disabled","disabled");

        //删除按钮的点击事件
        $("#favorite").removeAttr("onclick");
        }else{
        // 用户没有收藏
        }
        });



        });

        //点击收藏按钮触发的方法
        function addFavorite(){
        var rid = getParameter("rid");
        //1. 判断用户是否登录
        $.get("user/findOne",{},function (user) {
        if(user){
        //用户登录了
        //添加功能
        $.get("route/addFavorite",{rid:rid},function () {

        //代码刷新页面
        location.reload();
        });

        }else{
        //用户没有登录
        alert("您尚未登录，请登录");
        location.href="http://localhost/travel/login.html";
        }
        })
        }


</script>
</body>

</html>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>东软旅游-搜索</title>
<link rel="stylesheet" type="text/css" href="css/common.css">
<link rel="stylesheet" href="css/search.css">
<script src="js/jquery-3.3.1.js"></script>
<script src="js/getParameter.js"></script>
<script>

        $(function () {
            */
/* var search = location.search;
             //alert(search);//?id=5
             // 切割字符串，拿到第二个值
             var cid = search.split("=")[1];*//*

                //获取cid的参数值
                var cid = getParameter("cid");
                //获取rname的参数值
                var rname = getParameter("rname");
                //判断rname如果不为null或者""
                if(rname){
                //url解码
                rname = window.decodeURIComponent(rname);
                }

                //当页码加载完成后，调用load方法，发送ajax请求加载数据
                load(cid,null,rname);
                });

                function load(cid ,currentPage,rname){
                //发送ajax请求，请求route/pageQuery,传递cid
                $.get("route/pageQuery",{cid:cid,currentPage:currentPage,rname:rname},function (pb) {
                //解析pagebean数据，展示到页面上

                //1.分页工具条数据展示
                //1.1 展示总页码和总记录数
                $("#totalPage").html(pb.totalPage);
                $("#totalCount").html(pb.totalCount);

                */
/*
                        <li><a href="">首页</a></li>
                        <li class="threeword"><a href="#">上一页</a></li>
                        <li class="curPage"><a href="#">1</a></li>
                        <li><a href="#">2</a></li>
                        <li><a href="#">3</a></li>
                        <li><a href="#">4</a></li>
                        <li><a href="#">5</a></li>
                        <li><a href="#">6</a></li>
                        <li><a href="#">7</a></li>
                        <li><a href="#">8</a></li>
                        <li><a href="#">9</a></li>
                        <li><a href="#">10</a></li>
                        <li class="threeword"><a href="javascript:;">下一页</a></li>
                        <li class="threeword"><a href="javascript:;">末页</a></li>


                 *//*

                var lis = "";

                var fristPage = '<li onclick="javascipt:load('+cid+',1,\''+rname+'\')"><a href="javascript:void(0)">首页</a></li>';

                //计算上一页的页码
                var beforeNum =  pb.currentPage - 1;
                if(beforeNum <= 0){
                beforeNum = 1;
                }

                var beforePage = '<li  onclick="javascipt:load('+cid+','+beforeNum+',\''+rname+'\')" class="threeword"><a href="javascript:void(0)">上一页</a></li>';

                lis += fristPage;
                lis += beforePage;
                //1.2 展示分页页码
                */
/*
                    1.一共展示10个页码，能够达到前5后4的效果
                    2.如果前边不够5个，后边补齐10个
                    3.如果后边不足4个，前边补齐10个
                *//*


                // 定义开始位置begin,结束位置 end
                var begin; // 开始位置
                var end ; //  结束位置


                //1.要显示10个页码
                if(pb.totalPage < 10){
        //总页码不够10页

        begin = 1;
        end = pb.totalPage;
        }else{
        //总页码超过10页

        begin = pb.currentPage - 5 ;
        end = pb.currentPage + 4 ;

        //2.如果前边不够5个，后边补齐10个
        if(begin < 1){
        begin = 1;
        end = begin + 9;
        }

        //3.如果后边不足4个，前边补齐10个
        if(end > pb.totalPage){
        end = pb.totalPage;
        begin = end - 9 ;
        }
        }


        for (var i = begin; i <= end ; i++) {
        var li;
        //判断当前页码是否等于i
        if(pb.currentPage == i){

        li = '<li class="curPage" onclick="javascipt:load('+cid+','+i+',\''+rname+'\')"><a href="javascript:void(0)">'+i+'</a></li>';

        }else{
        //创建页码的li
        li = '<li onclick="javascipt:load('+cid+','+i+',\''+rname+'\')"><a href="javascript:void(0)">'+i+'</a></li>';
        }
        //拼接字符串
        lis += li;
        }





                */
/* for (var i = 1; i <= pb.totalPage ; i++) {
                     var li;
                     //判断当前页码是否等于i
                     if(pb.currentPage == i){

                         li = '<li class="curPage" onclick="javascipt:load('+cid+','+i+')"><a href="javascript:void(0)">'+i+'</a></li>';

                     }else{
                         //创建页码的li
                         li = '<li onclick="javascipt:load('+cid+','+i+')"><a href="javascript:void(0)">'+i+'</a></li>';
                     }
                     //拼接字符串
                     lis += li;
                 }*//*

        var lastPage = '<li class="threeword"><a href="javascript:;">末页</a></li>';
        var nextPage = '<li class="threeword"><a href="javascript:;">下一页</a></li>';

        lis += nextPage;
        lis += lastPage;


        //将lis内容设置到 ul
        $("#pageNum").html(lis);



                */
/*
                    <li>
                        <div class="img"><img src="images/04-search_03.jpg" alt=""></div>
                        <div class="text1">
                            <p>【减100元 含除夕/春节出发】广州增城三英温泉度假酒店/自由行套票</p>
                            <br/>
                            <p>1-2月出发，网付立享￥1099/2人起！爆款位置有限，抢完即止！</p>
                        </div>
                        <div class="price">
                            <p class="price_num">
                                <span>&yen;</span>
                                <span>299</span>
                                <span>起</span>
                            </p>
                            <p><a href="route_detail.html">查看详情</a></p>
                        </div>
                    </li>

                 *//*


        //2.列表数据展示
        var route_lis = "";

        for (var i = 0; i < pb.list.length; i++) {
        //获取{rid:1,rname:"xxx"}
        var route = pb.list[i];

        var li = '<li>\n' +
        '                        <div class="img"><img src="'+route.rimage+'" style="width: 299px;"></div>\n' +
        '                        <div class="text1">\n' +
        '                            <p>'+route.rname+'</p>\n' +
        '                            <br/>\n' +
        '                            <p>'+route.routeIntroduce+'</p>\n' +
        '                        </div>\n' +
        '                        <div class="price">\n' +
        '                            <p class="price_num">\n' +
        '                                <span>&yen;</span>\n' +
        '                                <span>'+route.price+'</span>\n' +
        '                                <span>起</span>\n' +
        '                            </p>\n' +
        '                            <p><a href="route_detail.html?rid='+route.rid+'">查看详情</a></p>\n' +
        '                        </div>\n' +
        '                    </li>';
        route_lis += li;
        }
        $("#route").html(route_lis);

        //定位到页面顶部
        window.scrollTo(0,0);
        });

        }


</script>
</head>
<body>
<!--引入头部-->
<div id="header"></div>
<div class="page_one">
<div class="contant">
<div class="crumbs">
<img src="images/search.png" alt="">
<p>东软旅行><span>搜索结果</span></p>
</div>
<div class="xinxi clearfix">
<div class="left">
<div class="header">
<span>商品信息</span>
<span class="jg">价格</span>
</div>
<ul id="route">
<li>
<div class="img"><img src="images/04-search_03.jpg" alt=""></div>
<div class="text1">
<p>【减100元 含除夕/春节出发】广州增城三英温泉度假酒店/自由行套票</p>
<br/>
<p>1-2月出发，网付立享￥1099/2人起！爆款位置有限，抢完即止！</p>
</div>
<div class="price">
<p class="price_num">
<span>&yen;</span>
<span>299</span>
<span>起</span>
</p>
<p><a href="route_detail.html">查看详情</a></p>
</div>
</li>
<li>
<div class="img"><img src="images/04-search_03.jpg" alt=""></div>
<div class="text1">
<p>浪花朵朵旅行普吉岛丛林飞跃空中飞人探险游中文服务泰国旅游</p>
<br/>
<p>1-2月出发，网付立享￥1099/2人起！爆款位置有限，抢完即止！</p>
</div>
<div class="price">
<p class="price_num">
<span>&yen;</span>
<span>899</span>
<span>起</span>
</p>
<p><a href="route_detail.html">查看详情</a></p>
</div>
</li>
<li>
<div class="img"><img src="images/04-search_03.jpg" alt=""></div>
<div class="text1">
<p>黑妞皇家旅行普吉岛攀牙湾大船星光之旅皮划艇日落休闲特色体验</p>
<br/>
<p>1-2月出发，网付立享￥1099/2人起！爆款位置有限，抢完即止！</p>
</div>
<div class="price">
<p class="price_num">
<span>&yen;</span>
<span>999</span>
<span>起</span>
</p>
<p><a href="route_detail.html">查看详情</a></p>
</div>
</li>
<li>
<div class="img"><img src="images/04-search_03.jpg" alt=""></div>
<div class="text1">
<p>浪花朵朵旅行普吉岛皇帝岛珊瑚岛香蕉船拖拽伞水上项目</p>
<br/>
<p>1-2月出发，网付立享￥1099/2人起！爆款位置有限，抢完即止！</p>
</div>
<div class="price">
<p class="price_num">
<span>&yen;</span>
<span>99</span>
<span>起</span>
</p>
<p><a href="route_detail.html">查看详情</a></p>
</div>
</li>
<li>
<div class="img"><img src="images/04-search_03.jpg" alt=""></div>
<div class="text1">
<p>环游记 泰国清迈Lila massage女子监狱spa 丽菈泰式按摩马杀鸡</p>
<br/>
<p>1-2月出发，网付立享￥1099/2人起！爆款位置有限，抢完即止！</p>
</div>
<div class="price">
<p class="price_num">
<span>&yen;</span>
<span>199</span>
<span>起</span>
</p>
<p><a href="route_detail.html">查看详情</a></p>
</div>
</li>
<li>
<div class="img"><img src="images/04-search_03.jpg" alt=""></div>
<div class="text1">
<p>【减100元 含除夕/春节出发】广州增城三英温泉度假酒店/自由行套票</p>
<br/>
<p>1-2月出发，网付立享￥1099/2人起！爆款位置有限，抢完即止！</p>
</div>
<div class="price">
<p class="price_num">
<span>&yen;</span>
<span>899</span>
<span>起</span>
</p>
<p><a href="route_detail.html">查看详情</a></p>
</div>
</li>
<li>
<div class="img"><img src="images/04-search_03.jpg" alt=""></div>
<div class="text1">
<p>【减100元 含除夕/春节出发】广州增城三英温泉度假酒店/自由行套票</p>
<br/>
<p>1-2月出发，网付立享￥1099/2人起！爆款位置有限，抢完即止！</p>
</div>
<div class="price">
<p class="price_num">
<span>&yen;</span>
<span>1199</span>
<span>起</span>
</p>
<p><a href="route_detail.html">查看详情</a></p>
</div>
</li>
<li>
<div class="img"><img src="images/04-search_03.jpg" alt=""></div>
<div class="text1">
<p>泰国芭提雅三合一日游芭提雅蒂芬妮人妖秀成人门票bigeye含接送</p>
<br/>
<p>1-2月出发，网付立享￥1099/2人起！爆款位置有限，抢完即止！</p>
</div>
<div class="price">
<p class="price_num">
<span>&yen;</span>
<span>1589</span>
<span>起</span>
</p>
<p><a href="route_detail.html">查看详情</a></p>
</div>
</li>
</ul>
<div class="page_num_inf">
<i></i> 共
<span id="totalPage"></span>页<span id="tatalCount"></span>条
</div>
<div class="pageNum">
<ul id="pageNum">
<li><a href="">首页</a></li>
<li class="threeword"><a href="#">上一页</a></li>
<li><a href="#">1</a></li>
<li><a href="#">2</a></li>
<li><a href="#">3</a></li>
<li><a href="#">4</a></li>
<li><a href="#">5</a></li>
<li><a href="#">6</a></li>
<li><a href="#">7</a></li>
<li><a href="#">8</a></li>
<li><a href="#">9</a></li>
<li><a href="#">10</a></li>
<li class="threeword"><a href="javascript:;">下一页</a></li>
<li class="threeword"><a href="javascript:;">末页</a></li>
</ul>
</div>
</div>
<div class="right">
<div class="top">
<div class="hot">HOT</div>
<span>热门推荐</span>
</div>
<ul>
<li>
<div class="left"><img src="images/04-search_09.jpg" alt=""></div>
<div class="right">
<p>清远新银盏温泉度假村酒店/自由行套...</p>
<p>网付价<span>&yen;<span>899</span>起</span>
</p>
</div>
</li>
<li>
<div class="left"><img src="images/04-search_09.jpg" alt=""></div>
<div class="right">
<p>清远新银盏温泉度假村酒店/自由行套...</p>
<p>网付价<span>&yen;<span>899</span>起</span>
</p>
</div>
</li>
<li>
<div class="left"><img src="images/04-search_09.jpg" alt=""></div>
<div class="right">
<p>清远新银盏温泉度假村酒店/自由行套...</p>
<p>网付价<span>&yen;<span>899</span>起</span>
</p>
</div>
</li>
<li>
<div class="left"><img src="images/04-search_09.jpg" alt=""></div>
<div class="right">
<p>清远新银盏温泉度假村酒店/自由行套...</p>
<p>网付价<span>&yen;<span>899</span>起</span>
</p>
</div>
</li>
<li>
<div class="left"><img src="images/04-search_09.jpg" alt=""></div>
<div class="right">
<p>清远新银盏温泉度假村酒店/自由行套...</p>
<p>网付价<span>&yen;<span>899</span>起</span>
</p>
</div>
</li>
</ul>
</div>
</div>
</div>
</div>

<!--引入头部-->
<div id="footer"></div>
<!--导入布局js，共享header和footer-->
<script type="text/javascript" src="js/include.js"></script>

*/
