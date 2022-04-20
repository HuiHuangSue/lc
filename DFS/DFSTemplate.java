public class DFSTemplate {
    public ReturnType dfs(paramsList) {
        if (递归出口) {
            记录答案;
            return;
        }
        for (所有的拆解可能性) {
            修改所有的参数;
            dfs(参数列表);
            还原所有被修改过的参数
        }
        return something 如果需要的话，很多时候不需要 return 值除了分治的写法
    }
}