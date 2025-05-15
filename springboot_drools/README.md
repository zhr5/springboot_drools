Drools 是一个基于规则的业务逻辑决策框架，允许开发者通过规则引擎来实现复杂的业务规则管理。在 Drools 中，规则（Rule）是定义在 [.drl](file://F:\Work\springboot_drools\springboot_drools\target\classes\rule.drl) 文件中的，每条规则可以包含多个属性。

以下是 Drools 规则中常见的 **规则属性（Rule Attributes）**：

### 1. `salience`
- 控制规则的优先级，默认值为 0。
- 数值越大，优先级越高。
- 示例：`salience 10`

### 2. `enabled`
- 控制规则是否启用。
- 可选值：`true` 或 `false`。
- 示例：`enabled true`

### 3. `no-loop`
- 防止规则因自身修改了事实而再次触发。
- 可选值：`true` 或 `false`。
- 示例：`no-loop true`

### 4. `agenda-group`
- 将规则分配到指定的议程组，用于分组激活规则。
- 示例：`agenda-group "group1"`

### 5. `activation-group`
- 同一组中只有一个规则会被激活（互斥规则）。
- 示例：`activation-group "group1"`

### 6. [duration](file://F:\Work\springboot_drools\springboot_drools\src\main\java\com\jobs\entity\ParkingCalculation.java#L7-L7)
- 指定规则在多少毫秒后过期。
- 示例：`duration 5000`

### 7. `date-effective` / `date-expires`
- 设置规则生效和失效的时间。
- 格式需符合日期格式（如：`"2024-01-01"`）。
- 示例：
  ```java
  date-effective "2024-01-01"
  date-expires "2025-01-01"
  ```


### 8. `lock-on-active`
- 设置为 `true` 时，该规则一旦被激活就不会再重新匹配新的事实。
- 示例：`lock-on-active true`

### 9. `auto-focus`
- 当规则属于某个议程组时，设置此属性可自动将焦点切换到该组。
- 示例：`auto-focus true`

### 示例规则结构：
```java
rule "Example Rule"
    salience 10
    no-loop true
    agenda-group "exampleGroup"
    when
        // 条件部分
    then
        // 动作部分
end
```


