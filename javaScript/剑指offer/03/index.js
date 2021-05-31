var findRepeatNumber = function(nums) {
    let visited = {}
    for(let i=0;i<nums.length;i++)
    {
        if(visited[nums[i]])
        {
            return nums[i]
        }
        else
        {
            visited[nums[i]]=1
        }
    }
};

console.log(findRepeatNumber([2,3,1,0,2,5,3]))